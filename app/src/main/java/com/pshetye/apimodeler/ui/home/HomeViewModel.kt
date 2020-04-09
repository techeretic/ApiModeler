package com.pshetye.apimodeler.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pshetye.apimodeler.R
import com.pshetye.apimodeler.ui.home.models.SupportedModule

class HomeViewModel : ViewModel() {
    val supportedModules: LiveData<List<SupportedModule>> = MutableLiveData<List<SupportedModule>>()
        .apply {
            value = listOf(
                SupportedModule(
                    R.string.title_usgs,
                    R.id.action_navigation_home_to_usgsEarthquakes,
                    "https://earthquake.usgs.gov/"
                ),
                SupportedModule(
                    R.string.title_covid19,
                    R.id.action_navigation_home_to_covid,
                    "https://corona-api.com/"
                ),
                SupportedModule(
                    R.string.title_cacovid19,
                    R.id.action_navigation_home_to_cacovid,
                    "https://data.chhs.ca.gov/"
                )
            )
        }
}