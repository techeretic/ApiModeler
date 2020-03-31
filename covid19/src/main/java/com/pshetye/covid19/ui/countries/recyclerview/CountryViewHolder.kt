package com.pshetye.covid19.ui.countries.recyclerview

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.pshetye.covid19.R
import com.pshetye.covid19.models.Data

class CountryViewHolder(
    private val view: View
) : RecyclerView.ViewHolder(view) {
    fun bind(data: Data) {
        view.findViewById<TextView>(R.id.country_name).text = data.name
        with(data.latest_data!!) {
            view.findViewById<TextView>(R.id.cases).text = view.context
                .getString(R.string.number_of_cases, confirmed)
            view.findViewById<TextView>(R.id.deaths).text = view.context
                .getString(R.string.number_of_deaths, deaths)
        }
    }
}