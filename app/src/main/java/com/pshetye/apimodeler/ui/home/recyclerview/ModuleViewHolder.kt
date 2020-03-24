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
        view.findViewById<TextView>(R.id.module_name).text = supportedModule.moduleName
        view.setOnClickListener(
            Navigation.createNavigateOnClickListener(supportedModule.navigationRes)
        )
    }
}