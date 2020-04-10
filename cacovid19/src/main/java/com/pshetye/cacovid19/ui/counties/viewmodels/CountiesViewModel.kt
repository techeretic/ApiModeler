package com.pshetye.cacovid19.ui.counties.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pshetye.cacovid19.R
import com.pshetye.cacovid19.models.toViewDataModel
import com.pshetye.cacovid19.repository.network.CaCovid19Service
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class CountiesViewModel @Inject constructor(
    private val caCovid19Service: CaCovid19Service
) : ViewModel() {

    private val dateFormat1: DateFormat = SimpleDateFormat("M/d/YYY", Locale.getDefault())
    private val dateFormat2: DateFormat = SimpleDateFormat("MM/dd/YYY", Locale.getDefault())

    val countiesLiveData: MutableLiveData<List<CountiesViewDataModel>> =
        MutableLiveData()

    fun fetchCaliforniaCovidStatsViaSql(): Disposable {
        return caCovid19Service.getCaliforniaCasesViaSql(getQuery(todayFormat1, todayFormat2))
            .flatMap {
                if (it.result.records.isEmpty()) {
                    caCovid19Service.getCaliforniaCasesViaSql(getQuery(yesterdayFormat1, yesterdayFormat2))
                } else {
                    Single.just(it)
                }
            }
            .map { caliCovidStats ->
                val result = caliCovidStats.result.records
                    .filter { (it.totalConfirmedCases ?: 0) > 0 }
                    .sortedByDescending { it.totalConfirmedCases }
                    .map { it.toViewDataModel() }
                generateTotals(result)
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                countiesLiveData.postValue(it)
            }, {
                Log.e(CountiesViewModel::class.simpleName, "Error -> $it", it)
            })
    }

    private fun generateTotals(result: List<CountyViewDataModel>): List<CountiesViewDataModel> {
        var cases: Long = 0
        var critical: Long = 0
        var deaths: Long = 0
        result.forEach {
            cases += it.cases
            critical += it.critical
            deaths += it.deaths
        }
        return mutableListOf<CountiesViewDataModel>(
            TotalsViewDataModel(
                totalCounties = result.size,
                headerStringRes = R.string.title_cacovid19,
                cases = cases,
                critical = critical,
                deaths = deaths
            )
        ).apply {
            addAll(result)
        }
    }

    private fun getQuery(date1: String, date2: String): String =
        "SELECT * from \"6cd8d424-dfaa-4bdd-9410-a3d656e1176e\" " +
            "WHERE \"Most Recent Date\" like '$date1' OR \"Most Recent Date\" like '$date2'"

    private val yesterdayFormat1: String
        get() {
            with(Calendar.getInstance()) {
                add(Calendar.DATE, -1)
                return dateFormat1.format(time)
            }
        }

    private val todayFormat1: String
        get() {
            with(Calendar.getInstance()) {
                return dateFormat1.format(time)
            }
        }

    private val yesterdayFormat2: String
        get() {
            with(Calendar.getInstance()) {
                add(Calendar.DATE, -1)
                return dateFormat2.format(time)
            }
        }

    private val todayFormat2: String
        get() {
            with(Calendar.getInstance()) {
                return dateFormat2.format(time)
            }
        }
}