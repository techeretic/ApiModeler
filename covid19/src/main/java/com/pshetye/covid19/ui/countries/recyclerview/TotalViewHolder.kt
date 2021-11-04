package com.pshetye.covid19.ui.countries.recyclerview

import android.icu.text.NumberFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.pshetye.covid19.R
import com.pshetye.covid19.ui.countries.viewmodels.TotalViewDataModel

class TotalViewHolder(
    private val view: View
) : RecyclerView.ViewHolder(view) {
    fun bind(data: TotalViewDataModel) = with(view) {
        val numberFormat = NumberFormat.getInstance()
        findViewById<TextView>(R.id.title).setText(data.title)
        view.findViewById<TextView>(R.id.last_updated).text = context.getString(R.string.as_of, data.updatedAt)
        view.findViewById<TextView>(R.id.countries).text = numberFormat.format(data.countries)
        view.findViewById<TextView>(R.id.cases).text = numberFormat.format(data.cases)
        view.findViewById<TextView>(R.id.recoveries).text = numberFormat.format(data.recovered)
        view.findViewById<TextView>(R.id.critical).text = numberFormat.format(data.critical)
        view.findViewById<TextView>(R.id.deaths).text = numberFormat.format(data.deaths)
    }
}

fun createTotalViewHolder(parent: ViewGroup): TotalViewHolder =
    with(LayoutInflater.from(parent.context)) {
        TotalViewHolder(inflate(R.layout.total_view, parent, false))
    }
