package com.pshetye.cacovid19.ui.counties.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Reusable
import javax.inject.Inject
import javax.inject.Provider

@Suppress("UNCHECKED_CAST")
@Reusable
class CountiesViewModelFactory @Inject constructor(
    private val countiesViewModelProvider: Provider<CountiesViewModel>
): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return countiesViewModelProvider.get() as T
    }
}