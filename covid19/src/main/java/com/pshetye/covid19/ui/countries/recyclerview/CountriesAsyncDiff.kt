package com.pshetye.covid19.ui.countries.recyclerview

import androidx.recyclerview.widget.DiffUtil
import com.pshetye.covid19.models.Data
import com.pshetye.covid19.ui.countries.viewmodels.CountryViewDataModel
import dagger.Reusable
import javax.inject.Inject

@Reusable
class CountriesAsyncDiff @Inject constructor() : DiffUtil.ItemCallback<CountryViewDataModel>() {
    override fun areItemsTheSame(
        oldItem: CountryViewDataModel,
        newItem: CountryViewDataModel
    ): Boolean =
        oldItem.countryName == newItem.countryName

    override fun areContentsTheSame(
        oldItem: CountryViewDataModel,
        newItem: CountryViewDataModel
    ): Boolean =
        oldItem.countryName == newItem.countryName
}