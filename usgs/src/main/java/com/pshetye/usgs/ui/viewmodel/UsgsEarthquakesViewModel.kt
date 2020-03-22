package com.pshetye.usgs.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.pshetye.usgs.repository.network.UsgsService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class UsgsEarthquakesViewModel @Inject constructor(
    private val usgsService: UsgsService
) : ViewModel() {
    fun fetchEarthQuakes(): Disposable {
        return usgsService.getEarthquakes(ALL_EQ_IN_LAST_HOUR)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.d(UsgsEarthquakesViewModel::class.simpleName, "${it.features?.size}")
            }, { error ->
                Log.e(UsgsEarthquakesViewModel::class.simpleName, "Error", error)
            })
    }
}

private const val ALL_EQ_IN_LAST_HOUR = "all_hour.geojson"
private const val GREATER_THAN_1_IN_LAST_HOUR = "1.0_hour.geojson"
private const val GREATER_THAN_2_5_IN_LAST_HOUR = "2.5_hour.geojson"
private const val GREATER_THAN_4_5_IN_LAST_HOUR = "4.5_hour.geojson"
private const val SIGNIFICANT_EQ_IN_LAST_HOUR = "significant_hour.geojson"