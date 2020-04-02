package com.pshetye.covid19.ui.countries.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.pshetye.covid19.R
import com.pshetye.covid19.models.Data
import com.pshetye.covid19.ui.countries.viewmodels.*
import javax.inject.Inject

class CountriesAdapter @Inject constructor(
    countriesAsyncDiff: CountriesAsyncDiff
) : ListAdapter<ViewDataModel, RecyclerView.ViewHolder>(countriesAsyncDiff) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        when(viewType) {
            TOTAL_VIEW_TYPE -> TotalViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.total_item_view, parent, false)
            )
            COUNTRY_VIEW_TYPE -> CountryViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.country_item_view, parent, false)
            )
            else -> throw UnsupportedOperationException("$viewType is not supported")
        }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(getItem(position)) {
            is TotalViewDataModel ->
                (holder as TotalViewHolder).bind(getItem(position) as TotalViewDataModel)
            is CountryViewDataModel ->
                (holder as CountryViewHolder).bind(getItem(position) as CountryViewDataModel)
        }
    }

    override fun getItemViewType(position: Int): Int =
        when(getItem(position)) {
            is TotalViewDataModel -> TOTAL_VIEW_TYPE
            is CountryViewDataModel -> COUNTRY_VIEW_TYPE
        }
}