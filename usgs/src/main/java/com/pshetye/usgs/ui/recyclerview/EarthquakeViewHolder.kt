package com.pshetye.usgs.ui.recyclerview

import android.icu.text.NumberFormat
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.pshetye.usgs.R
import com.pshetye.usgs.models.Feature

class EarthquakeViewHolder(
    private val view: View
) : RecyclerView.ViewHolder(view) {
    fun bind(feature: Feature) {
        with(view) {
            findViewById<TextView>(R.id.earthquake_place).text =
                feature.properties?.place ?: "no-where"
            findViewById<TextView>(R.id.intensity).text =
                context.getString(R.string.eq_intensity,
                    NumberFormat.getInstance().format(feature.properties?.mag ?: 0.0))
        }
    }
}