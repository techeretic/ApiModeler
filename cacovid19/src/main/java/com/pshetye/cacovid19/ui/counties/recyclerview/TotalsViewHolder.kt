package com.pshetye.cacovid19.ui.counties.recyclerview

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.pshetye.cacovid19.R
import com.pshetye.cacovid19.ui.counties.viewmodels.TotalsViewDataModel
import kotlinx.android.synthetic.main.total_counties_item_view.view.*

class TotalsViewHolder(
    private val view: View
): RecyclerView.ViewHolder(view) {
    fun bind(data: TotalsViewDataModel) = with(view) {
        val numberFormat = android.icu.text.NumberFormat.getInstance()
        title.setText(data.headerStringRes)
        last_updated.text = context.getString(R.string.as_of, data.lastUpdatedDate)
        counties.text = view.context
            .getString(R.string.number_of_counties, numberFormat.format(data.totalCounties))
        cases.text = view.context
            .getString(R.string.number_of_cases, numberFormat.format(data.cases))
        critical.text = view.context
            .getString(R.string.number_of_critical, numberFormat.format(data.critical))
        deaths.text = view.context
            .getString(R.string.number_of_deaths, numberFormat.format(data.deaths))
    }
}