package com.pshetye.covid19.ui.countries.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {
    val sortOptionLiveData: MutableLiveData<String> = MutableLiveData()
}