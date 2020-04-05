package com.pshetye.covid19.ui.countries.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pshetye.apimodeler.kit.ratelimit.RateLimiter
import com.pshetye.apimodeler.kit.ratelimit.RateLimiterFactory
import com.pshetye.covid19.R
import com.pshetye.covid19.models.toViewDataModel
import com.pshetye.covid19.repository.network.Covid19Service
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.disposables.Disposables
import io.reactivex.schedulers.Schedulers
import java.util.Calendar
import javax.inject.Inject

class CountriesViewModel @Inject constructor(
    private val covid19Service: Covid19Service,
    rateLimiterFactory: RateLimiterFactory,
    private val calendar: Calendar
) : ViewModel() {
    val covid19CasesPerCountry: MutableLiveData<List<ViewDataModel>> = MutableLiveData()

    private val inMemoryCache: MutableList<ViewDataModel> = mutableListOf()

    private val rateLimiter: RateLimiter = rateLimiterFactory.create(NEXT_REQUEST_INTERVAL)

    fun fetchCovidStatus(): Disposable {
        return if (!rateLimiter.shouldPerformRequest() && inMemoryCache.isNotEmpty()) {
            covid19CasesPerCountry.postValue(inMemoryCache)
            Disposables.empty()
        } else {
            rateLimiter.timeOfRequestInMilliseconds = calendar.timeInMillis
            inMemoryCache.clear()
            fetchCovidInfoFromNetwork()
        }

    }

    private fun fetchCovidInfoFromNetwork(): Disposable {
        return covid19Service.getCountries()
            .map { response ->
                val result = (response.data ?: emptyList())
                    .filter { (it.latest_data?.confirmed ?: 0) > 0 }
                    .sortedByDescending { it.latest_data!!.confirmed }
                    .map { it.toViewDataModel()!! }
                var cases: Long = 0
                var recovered: Long = 0
                var deaths: Long = 0
                result.forEach {
                    cases += it.cases
                    recovered += it.recovered
                    deaths += it.deaths
                }
                mutableListOf<ViewDataModel>(
                    TotalViewDataModel(
                        title = R.string.worldwide_total,
                        countries = result.size,
                        cases = cases,
                        recovered = recovered,
                        deaths = deaths
                    )
                ).apply { addAll(result) }
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                inMemoryCache.addAll(it)
                covid19CasesPerCountry.postValue(it)
            }, { error ->
                Log.e(CountriesViewModel::class.simpleName, "Error", error)
            })
    }
}

private const val NEXT_REQUEST_INTERVAL = 3000000L