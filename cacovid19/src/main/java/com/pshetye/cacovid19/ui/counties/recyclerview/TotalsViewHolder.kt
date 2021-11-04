package com.pshetye.cacovid19.ui.counties.recyclerview

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.pshetye.cacovid19.R
import com.pshetye.cacovid19.ui.counties.viewmodels.TotalsViewDataModel

class TotalsViewHolder(
    private val view: View
): RecyclerView.ViewHolder(view) {
    fun bind(data: TotalsViewDataModel) = with(view) {
        val numberFormat = android.icu.text.NumberFormat.getInstance()
        view.findViewById<TextView>(R.id.title).setText(data.headerStringRes)
        view.findViewById<TextView>(R.id.last_updated).text = context.getString(R.string.as_of, data.lastUpdatedDate)
        view.findViewById<TextView>(R.id.counties).text = view.context
            .getString(R.string.number_of_counties, numberFormat.format(data.totalCounties))
        view.findViewById<TextView>(R.id.cases).text = view.context
            .getString(R.string.number_of_cases, numberFormat.format(data.cases))
        view.findViewById<TextView>(R.id.critical).text = view.context
            .getString(R.string.number_of_critical, numberFormat.format(data.critical))
        view.findViewById<TextView>(R.id.deaths).text = view.context
            .getString(R.string.number_of_deaths, numberFormat.format(data.deaths))
    }
}