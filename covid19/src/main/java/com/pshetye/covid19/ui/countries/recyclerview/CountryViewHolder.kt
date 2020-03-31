package com.pshetye.covid19.ui.countries.recyclerview

import android.icu.text.NumberFormat
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.pshetye.covid19.R
import com.pshetye.covid19.models.Data
import com.pshetye.covid19.ui.countries.viewmodels.CountryViewDataModel

class CountryViewHolder(
    private val view: View
) : RecyclerView.ViewHolder(view) {
    fun bind(data: CountryViewDataModel) {
        view.findViewById<TextView>(R.id.country_name).text = data.countryName
        view.findViewById<TextView>(R.id.cases).text = view.context
            .getString(R.string.number_of_cases, NumberFormat.getInstance().format(data.cases))
        view.findViewById<TextView>(R.id.deaths).text = view.context
            .getString(R.string.number_of_deaths, NumberFormat.getInstance().format(data.deaths))
    }
}