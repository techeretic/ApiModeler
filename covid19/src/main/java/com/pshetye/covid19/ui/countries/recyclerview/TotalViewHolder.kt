package com.pshetye.covid19.ui.countries.recyclerview

import android.icu.text.NumberFormat
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.pshetye.covid19.R
import com.pshetye.covid19.ui.countries.viewmodels.TotalViewDataModel

class TotalViewHolder(
    private val view: View
) : RecyclerView.ViewHolder(view) {
    fun bind(data: TotalViewDataModel) {
        view.findViewById<TextView>(R.id.title).setText(data.title)
        view.findViewById<TextView>(R.id.countries).text = view.context
            .getString(R.string.number_of_countries, NumberFormat.getInstance().format(data.countries))
        view.findViewById<TextView>(R.id.cases).text = view.context
            .getString(R.string.number_of_cases, NumberFormat.getInstance().format(data.cases))
        view.findViewById<TextView>(R.id.recoveries).text = view.context
            .getString(R.string.number_of_recoveries, NumberFormat.getInstance().format(data.recovered))
        view.findViewById<TextView>(R.id.deaths).text = view.context
            .getString(R.string.number_of_deaths, NumberFormat.getInstance().format(data.deaths))
    }
}