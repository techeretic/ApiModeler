package com.pshetye.covid19.ui.countries.recyclerview

import android.icu.text.NumberFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.pshetye.covid19.R
import com.pshetye.covid19.ui.countries.viewmodels.TotalViewDataModel
import kotlinx.android.synthetic.main.total_view.view.*

class TotalViewHolder(
    private val view: View
) : RecyclerView.ViewHolder(view) {
    fun bind(data: TotalViewDataModel) = with(view) {
        val numberFormat = NumberFormat.getInstance()
        findViewById<TextView>(R.id.title).setText(data.title)
        last_updated.text = context.getString(R.string.as_of, data.updatedAt)
        countries.text = numberFormat.format(data.countries)
        cases.text = numberFormat.format(data.cases)
        recoveries.text = numberFormat.format(data.recovered)
        critical.text = numberFormat.format(data.critical)
        deaths.text = numberFormat.format(data.deaths)
    }
}

fun createTotalViewHolder(parent: ViewGroup): TotalViewHolder =
    with(LayoutInflater.from(parent.context)) {
        TotalViewHolder(inflate(R.layout.total_view, parent, false))
    }
