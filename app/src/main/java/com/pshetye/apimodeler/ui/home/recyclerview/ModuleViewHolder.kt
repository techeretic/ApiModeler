package com.pshetye.apimodeler.ui.home.recyclerview

import android.view.View
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.pshetye.apimodeler.R
import com.pshetye.apimodeler.ui.home.models.SupportedModule

class ModuleViewHolder(
    private val view: View
) : RecyclerView.ViewHolder(view) {
    fun bind(supportedModule: SupportedModule) {
        with(view) {
            findViewById<TextView>(R.id.module_name).setText(supportedModule.headerTitle)
            findViewById<TextView>(R.id.api_source).text = supportedModule.apiSource
            setOnClickListener(
                Navigation.createNavigateOnClickListener(supportedModule.navigationRes)
            )
        }
    }
}