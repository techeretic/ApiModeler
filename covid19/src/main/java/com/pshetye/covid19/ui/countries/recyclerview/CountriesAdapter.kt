package com.pshetye.covid19.ui.countries.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.pshetye.covid19.R
import com.pshetye.covid19.models.Data
import javax.inject.Inject

class CountriesAdapter @Inject constructor(
    countriesAsyncDiff: CountriesAsyncDiff
) : ListAdapter<Data, RecyclerView.ViewHolder>(countriesAsyncDiff) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return CountryViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.country_item_view, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as CountryViewHolder).bind(getItem(position))
    }
}