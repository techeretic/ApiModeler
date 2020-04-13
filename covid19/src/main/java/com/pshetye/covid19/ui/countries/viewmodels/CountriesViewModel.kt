package com.pshetye.covid19.ui.countries.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pshetye.covid19.R
import com.pshetye.covid19.models.Countries
import com.pshetye.covid19.models.toViewDataModel
import com.pshetye.covid19.repository.network.Covid19Service
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.*
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
                val filtered = getFilteredData(response)
                val result = if (sortedBy != SORT_OPTION_ALPHABETICAL) {
                    filtered.sortedByDescending {
                        when(sortedBy) {
                            SORT_OPTION_CASES -> it.latest_data!!.confirmed
                            SORT_OPTION_RECOVERIES -> it.latest_data!!.recovered
                            SORT_OPTION_CRITICAL -> it.latest_data!!.critical
                            SORT_OPTION_DEATHS -> it.latest_data!!.deaths
                            else -> it.latest_data!!.confirmed
                        }
                    }
                } else {
                    filtered
                }.map { it.toViewDataModel(sortedBy)!! }

                mutableListOf<ViewDataModel>(getTotalViewDataModel(result, sortedBy))
                    .apply { addAll(result) }
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

    private fun getFilteredData(response: Countries) =
        (response.data ?: emptyList())
            .filter { (it.latest_data?.confirmed ?: 0) > 0 }

    private fun getTotalViewDataModel(
        countries: List<CountryViewDataModel>,
        @SortedBy sortedBy: String
    ): TotalViewDataModel {
        var cases: Long = 0
        var recovered: Long = 0
        var critical: Long = 0
        var deaths: Long = 0

        countries.forEachIndexed { index, countryViewDataModel ->
            cases += countryViewDataModel.cases
            recovered += countryViewDataModel.recovered
            critical += countryViewDataModel.critical
            deaths += countryViewDataModel.deaths
            countryViewDataModel.localIndex = index + 1
        }
        return TotalViewDataModel(
            sortedBy = sortedBy,
            title = R.string.worldwide_total,
            countries = countries.size,
            cases = cases,
            recovered = recovered,
            critical = critical,
            deaths = deaths,
            updatedAt = if (countries.isNotEmpty()) {
                countries[0].updatedAt
            } else {
                Date()
            }
        )
    }
}