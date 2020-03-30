package com.pshetye.covid19.ui.countries.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Reusable
import javax.inject.Inject
import javax.inject.Provider

@Suppress("UNCHECKED_CAST")
@Reusable
class CountriesViewModelFactory @Inject constructor(
    private val countriesViewModelProvider: Provider<CountriesViewModel>
): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return countriesViewModelProvider.get() as T
    }
}