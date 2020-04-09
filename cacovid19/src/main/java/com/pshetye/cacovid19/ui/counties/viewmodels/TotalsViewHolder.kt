package com.pshetye.cacovid19.ui.counties.viewmodels

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.pshetye.cacovid19.R

class TotalsViewHolder(
    private val view: View
): RecyclerView.ViewHolder(view) {
    fun bind(data: TotalsViewDataModel) = with(view) {
        val numberFormat = android.icu.text.NumberFormat.getInstance()
        findViewById<TextView>(R.id.title).setText(data.headerStringRes)
        findViewById<TextView>(R.id.counties).text = view.context
            .getString(R.string.number_of_countries, numberFormat.format(data.totalCounties))
        findViewById<TextView>(R.id.cases).text = view.context
            .getString(R.string.number_of_cases, numberFormat.format(data.cases))
        findViewById<TextView>(R.id.critical).text = view.context
            .getString(R.string.number_of_critical, numberFormat.format(data.critical))
        findViewById<TextView>(R.id.deaths).text = view.context
            .getString(R.string.number_of_deaths, numberFormat.format(data.deaths))
    }
}