package com.pshetye.usgs.ui.recyclerview

import androidx.recyclerview.widget.DiffUtil
import com.pshetye.usgs.models.Feature

class EarthquakesAsyncDiff : DiffUtil.ItemCallback<Feature>() {
    override fun areItemsTheSame(oldItem: Feature, newItem: Feature): Boolean =
        oldItem.properties?.place == newItem.properties?.place

    override fun areContentsTheSame(oldItem: Feature, newItem: Feature): Boolean =
        oldItem.properties?.place == newItem.properties?.place
}