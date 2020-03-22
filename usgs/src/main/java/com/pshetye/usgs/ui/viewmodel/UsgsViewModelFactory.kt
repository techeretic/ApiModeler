package com.pshetye.usgs.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Reusable
import javax.inject.Inject
import javax.inject.Provider

@Suppress("UNCHECKED_CAST")
@Reusable
class UsgsViewModelFactory @Inject constructor(
    private val usgsViewModelProvider: Provider<UsgsEarthquakesViewModel>
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return usgsViewModelProvider.get() as T
    }
}