package com.pshetye.apimodeler.ui.home.recyclerview

import androidx.recyclerview.widget.DiffUtil
import com.pshetye.apimodeler.ui.home.models.SupportedModule

class ModulesAsyncDiff : DiffUtil.ItemCallback<SupportedModule>() {
    override fun areItemsTheSame(oldItem: SupportedModule, newItem: SupportedModule): Boolean =
        oldItem.headerTitle == newItem.headerTitle

    override fun areContentsTheSame(oldItem: SupportedModule, newItem: SupportedModule): Boolean =
        oldItem.headerTitle == newItem.headerTitle
}