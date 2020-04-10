package com.pshetye.covid19.ui.countries.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pshetye.covid19.R
import com.pshetye.covid19.models.toViewDataModel
import com.pshetye.covid19.repository.network.Covid19Service
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class CountriesViewModel @Inject constructor(
    private val covid19Service: Covid19Service
) : ViewModel() {
    val covid19CasesPerCountry: MutableLiveData<List<ViewDataModel>> = MutableLiveData()

    fun fetchCovidStatus(@SortedBy sortedBy: String): Disposable =
        fetchCovidInfoFromNetwork(sortedBy)

    private fun fetchCovidInfoFromNetwork(@SortedBy sortedBy: String): Disposable {
        return covid19Service.getCountries()
            .map { response ->
                val result = (response.data ?: emptyList())
                    .filter { (it.latest_data?.confirmed ?: 0) > 0 }
                    .sortedByDescending {
                        when(sortedBy) {
                            SORT_OPTION_CASES -> it.latest_data!!.confirmed
                            SORT_OPTION_RECOVERIES -> it.latest_data!!.recovered
                            SORT_OPTION_CRITICAL -> it.latest_data!!.critical
                            SORT_OPTION_DEATHS -> it.latest_data!!.deaths
                            else -> it.latest_data!!.confirmed
                        }
                    }
                    .map { it.toViewDataModel(sortedBy)!! }
                var cases: Long = 0
                var recovered: Long = 0
                var critical: Long = 0
                var deaths: Long = 0
                result.forEach {
                    cases += it.cases
                    recovered += it.recovered
                    critical += it.critical
                    deaths += it.deaths
                }
                mutableListOf<ViewDataModel>(
                    TotalViewDataModel(
                        sortedBy = sortedBy,
                        title = R.string.worldwide_total,
                        countries = result.size,
                        cases = cases,
                        recovered = recovered,
                        critical = critical,
                        deaths = deaths
                    )
                ).apply { addAll(result) }
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                covid19CasesPerCountry.postValue(it)
            }, { error ->
                covid19CasesPerCountry.postValue(listOf(ErrorViewDataModel("")))
                Log.e(CountriesViewModel::class.simpleName, "Error", error)
            })
    }
}