package com.pshetye.cacovid19.ui.counties.recyclerview

import android.icu.text.NumberFormat
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.pshetye.cacovid19.R
import com.pshetye.cacovid19.ui.counties.viewmodels.CountyViewDataModel

class CountyViewHolder(
    private val view: View
): RecyclerView.ViewHolder(view) {
    fun bind(data: CountyViewDataModel) = with(view) {
        val numberFormat = NumberFormat.getInstance()
        findViewById<TextView>(R.id.county_name).text = data.countyName
        findViewById<TextView>(R.id.cases).text = view.context
            .getString(R.string.number_of_cases, numberFormat.format(data.cases))
        findViewById<TextView>(R.id.critical).text = view.context
            .getString(R.string.number_of_critical, numberFormat.format(data.critical))
        findViewById<TextView>(R.id.deaths).text = view.context
            .getString(R.string.number_of_deaths, numberFormat.format(data.deaths))
    }
}