package com.pshetye.apimodeler.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pshetye.apimodeler.R
import com.pshetye.apimodeler.ui.home.models.SupportedModule

class HomeViewModel : ViewModel() {
    val supportedModules: LiveData<List<SupportedModule>> = MutableLiveData<List<SupportedModule>>()
        .apply {
            value = listOf(SupportedModule("USGS Earthquakes", R.id.action_navigation_home_to_usgsEarthquakes))
        }
}