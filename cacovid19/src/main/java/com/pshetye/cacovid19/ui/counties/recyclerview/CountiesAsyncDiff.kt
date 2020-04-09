package com.pshetye.cacovid19.ui.counties.recyclerview

import androidx.recyclerview.widget.DiffUtil
import com.pshetye.cacovid19.ui.counties.viewmodels.CountiesViewDataModel
import javax.inject.Inject

class CountiesAsyncDiff @Inject constructor() : DiffUtil.ItemCallback<CountiesViewDataModel>() {
    override fun areItemsTheSame(
        oldItem: CountiesViewDataModel,
        newItem: CountiesViewDataModel
    ): Boolean = oldItem.uid == newItem.uid

    override fun areContentsTheSame(
        oldItem: CountiesViewDataModel,
        newItem: CountiesViewDataModel
    ): Boolean = oldItem.uid == newItem.uid
}