package com.pshetye.covid19.ui.countries.recyclerview

import androidx.recyclerview.widget.DiffUtil
import com.pshetye.covid19.ui.countries.viewmodels.ViewDataModel
import dagger.Reusable
import javax.inject.Inject

@Reusable
class CountriesAsyncDiff @Inject constructor() : DiffUtil.ItemCallback<ViewDataModel>() {
    override fun areItemsTheSame(
        oldItem: ViewDataModel,
        newItem: ViewDataModel
    ): Boolean =
        oldItem.uid == newItem.uid

    override fun areContentsTheSame(
        oldItem: ViewDataModel,
        newItem: ViewDataModel
    ): Boolean =
        oldItem.uid == newItem.uid
}