package com.pshetye.covid19.ui.countries.recyclerview

import android.icu.text.NumberFormat
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.pshetye.covid19.R
import com.pshetye.covid19.ui.countries.viewmodels.CountryViewDataModel

class CountryViewHolder(
    private val view: View
) : RecyclerView.ViewHolder(view) {
    fun bind(data: CountryViewDataModel, position: Int) = with(view) {
        val numberFormat = NumberFormat.getInstance()
        findViewById<TextView>(R.id.country_name).text =
            context.getString(R.string.ordered_name, position, data.countryName)
        findViewById<TextView>(R.id.cases).text = view.context
            .getString(R.string.number_of_cases, numberFormat.format(data.cases))
        findViewById<TextView>(R.id.recoveries).text = view.context
            .getString(R.string.number_of_recoveries, numberFormat.format(data.recovered))
        findViewById<TextView>(R.id.critical).text = view.context
            .getString(R.string.number_of_critical, numberFormat.format(data.critical))
        findViewById<TextView>(R.id.deaths).text = view.context
            .getString(R.string.number_of_deaths, numberFormat.format(data.deaths))
    }
}