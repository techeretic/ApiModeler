package com.pshetye.cacovid19.ui.counties.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.pshetye.cacovid19.R
import com.pshetye.cacovid19.ui.counties.viewmodels.*
import java.lang.IllegalStateException
import javax.inject.Inject

class CountiesAdapter @Inject constructor(
    countiesAsyncDiff: CountiesAsyncDiff
) : ListAdapter<CountiesViewDataModel, RecyclerView.ViewHolder>(countiesAsyncDiff) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when(viewType) {
            COUNTY_VIEW_TYPE -> CountyViewHolder(
                inflater.inflate(R.layout.county_item_view, parent, false)
            )
            TOTAL_VIEW_TYPE -> TotalsViewHolder(
                inflater.inflate(R.layout.total_counties_item_view, parent, false)
            )
            else -> throw IllegalStateException("Unsupported")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(getItem(position)) {
            is CountyViewDataModel ->
                (holder as CountyViewHolder).bind(getItem(position) as CountyViewDataModel)
            is TotalsViewDataModel ->
                (holder as TotalsViewHolder).bind(getItem(position) as TotalsViewDataModel)
        }
    }

    override fun getItemViewType(position: Int): Int =
        when(getItem(position)) {
            is CountyViewDataModel -> COUNTY_VIEW_TYPE
            is TotalsViewDataModel -> TOTAL_VIEW_TYPE
        }
}