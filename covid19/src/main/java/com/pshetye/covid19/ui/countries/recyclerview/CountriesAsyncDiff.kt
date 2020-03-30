package com.pshetye.covid19.ui.countries.recyclerview

import androidx.recyclerview.widget.DiffUtil
import com.pshetye.covid19.models.Data
import dagger.Reusable
import javax.inject.Inject

@Reusable
class CountriesAsyncDiff @Inject constructor() : DiffUtil.ItemCallback<Data>() {
    override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean =
        oldItem.name == newItem.name

    override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean =
        oldItem.name == newItem.name
}