package com.pshetye.usgs.ui.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.pshetye.usgs.R
import com.pshetye.usgs.di.scopes.USGS
import com.pshetye.usgs.models.Feature
import javax.inject.Inject

@USGS
class EarthquakesAdapter @Inject constructor() : ListAdapter<Feature, RecyclerView.ViewHolder>(EarthquakesAsyncDiff()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return EarthquakeViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.single_earthquake, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as EarthquakeViewHolder).bind(getItem(position))
    }
}