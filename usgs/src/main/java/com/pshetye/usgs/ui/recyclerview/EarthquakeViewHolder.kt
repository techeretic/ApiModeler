package com.pshetye.usgs.ui.recyclerview

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.pshetye.usgs.R
import com.pshetye.usgs.models.Feature

class EarthquakeViewHolder(
    private val view: View
) : RecyclerView.ViewHolder(view) {
    fun bind(feature: Feature) {
        view.findViewById<TextView>(R.id.earthquake_place).text = feature.properties?.place ?: "nowhere"
    }
}