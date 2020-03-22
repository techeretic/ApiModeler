package com.pshetye.apimodeler.ui.home.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.pshetye.apimodeler.R
import com.pshetye.apimodeler.di.scopes.PerFragment
import com.pshetye.apimodeler.ui.home.models.SupportedModule
import javax.inject.Inject

@PerFragment
class ModuleAdapter @Inject constructor() : ListAdapter<SupportedModule, RecyclerView.ViewHolder>(ModulesAsyncDiff()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        with(LayoutInflater.from(parent.context)) {
            return ModuleViewHolder(inflate(R.layout.module_entry_point, parent, false))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ModuleViewHolder).bind(getItem(position))
    }

    override fun getItemViewType(position: Int): Int {
        // We just have one type for now
        return SIMPLE_MODULE
    }
}