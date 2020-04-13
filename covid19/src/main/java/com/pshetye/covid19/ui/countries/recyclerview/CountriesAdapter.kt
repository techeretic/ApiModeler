package com.pshetye.covid19.ui.countries.recyclerview

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.pshetye.apimodeler.kit.viewholders.ErrorViewHolder
import com.pshetye.apimodeler.kit.viewholders.getErrorViewHolder
import com.pshetye.covid19.ui.countries.viewmodels.*
import javax.inject.Inject

class CountriesAdapter @Inject constructor(
    countriesAsyncDiff: CountriesAsyncDiff
) : ListAdapter<ViewDataModel, RecyclerView.ViewHolder>(countriesAsyncDiff) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        when(viewType) {
            TOTAL_VIEW_TYPE -> createTotalViewHolder(parent)
            COUNTRY_VIEW_TYPE -> createCountryViewHolder(parent)
            ERROR_VIEW_TYPE -> getErrorViewHolder(parent)
            else -> throw UnsupportedOperationException("$viewType is not supported")
        }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(getItem(position)) {
            is TotalViewDataModel ->
                (holder as TotalViewHolder).bind(getItem(position) as TotalViewDataModel)
            is CountryViewDataModel ->
                (holder as CountryViewHolder).bind(getItem(position) as CountryViewDataModel)
            is ErrorViewDataModel ->
                (holder as ErrorViewHolder).bind((getItem(position) as ErrorViewDataModel).errorMessage)
        }
    }

    override fun getItemViewType(position: Int): Int =
        when(getItem(position)) {
            is TotalViewDataModel -> TOTAL_VIEW_TYPE
            is CountryViewDataModel -> COUNTRY_VIEW_TYPE
            is ErrorViewDataModel -> ERROR_VIEW_TYPE
        }
}