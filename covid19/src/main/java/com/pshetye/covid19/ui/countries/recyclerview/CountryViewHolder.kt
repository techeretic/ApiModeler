package com.pshetye.covid19.ui.countries.recyclerview

import android.icu.text.NumberFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.pshetye.covid19.R
import com.pshetye.covid19.ui.countries.viewmodels.CountryViewDataModel
import kotlinx.android.synthetic.main.country_view.view.*

class CountryViewHolder(
    private val view: View
) : RecyclerView.ViewHolder(view) {
    fun bind(data: CountryViewDataModel) = with(view) {
        val numberFormat = NumberFormat.getInstance()
        country_name.text = context.getString(R.string.ordered_name, data.localIndex, data.countryName)
        cases.text = numberFormat.format(data.cases)
        recoveries.text = numberFormat.format(data.recovered)
        critical.text = numberFormat.format(data.critical)
        deaths.text = numberFormat.format(data.deaths)
    }
}

fun createCountryViewHolder(parent: ViewGroup): CountryViewHolder =
    with(LayoutInflater.from(parent.context)) {
        CountryViewHolder(inflate(R.layout.country_view, parent, false))
    }
