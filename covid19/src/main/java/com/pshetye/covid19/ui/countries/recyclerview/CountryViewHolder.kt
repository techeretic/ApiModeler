package com.pshetye.covid19.ui.countries.recyclerview

import android.icu.text.NumberFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.pshetye.covid19.R
import com.pshetye.covid19.ui.countries.viewmodels.CountryViewDataModel

class CountryViewHolder(
    private val view: View
) : RecyclerView.ViewHolder(view) {
    fun bind(data: CountryViewDataModel) = with(view) {
        val numberFormat = NumberFormat.getInstance()
        view.findViewById<TextView>(R.id.country_name).text = context.getString(R.string.ordered_name, data.localIndex, data.countryName)
        view.findViewById<TextView>(R.id.cases).text = numberFormat.format(data.cases)
        view.findViewById<TextView>(R.id.recoveries).text = numberFormat.format(data.recovered)
        view.findViewById<TextView>(R.id.critical).text = numberFormat.format(data.critical)
        view.findViewById<TextView>(R.id.deaths).text = numberFormat.format(data.deaths)
    }
}

fun createCountryViewHolder(parent: ViewGroup): CountryViewHolder =
    with(LayoutInflater.from(parent.context)) {
        CountryViewHolder(inflate(R.layout.country_view, parent, false))
    }
