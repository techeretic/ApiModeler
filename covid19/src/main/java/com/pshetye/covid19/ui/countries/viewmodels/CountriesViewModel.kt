package com.pshetye.covid19.ui.countries.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import com.pshetye.covid19.repository.network.Covid19Service
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class CountriesViewModel @Inject constructor(
    private val covid19Service: Covid19Service
) : ViewModel() {
    fun fetchCovidStatus(): Disposable {
        return covid19Service.getCountries()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.d(CountriesViewModel::class.simpleName, "Information for ${it.data?.size} countries")
                it.data?.forEach { country ->
                    if ("India" == country.name) {
                        Log.d(CountriesViewModel::class.simpleName,
                            "India has ${country?.latest_data?.confirmed} confirmed cases")
                    }
                }
            }, { error ->
                Log.e(CountriesViewModel::class.simpleName, "Error", error)
            })
    }
}