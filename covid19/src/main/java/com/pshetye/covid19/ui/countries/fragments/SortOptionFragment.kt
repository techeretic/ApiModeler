package com.pshetye.covid19.ui.countries.fragments

import android.content.Context
import android.os.Bundle
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.google.android.material.button.MaterialButton
import com.pshetye.covid19.R
import kotlinx.android.synthetic.main.fragment_sort_option_list_dialog.*
import kotlinx.android.synthetic.main.fragment_sort_option_list_dialog_item.view.*

class SortOptionFragment : BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_sort_option_list_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val sortOptions = getSortOptions(view.context)
        list.layoutManager = LinearLayoutManager(context)
        list.adapter = StringAdapter(sortOptions)
        list.adapter
    }

    private inner class ViewHolder internal constructor(
        inflater: LayoutInflater,
        parent: ViewGroup
    ) : RecyclerView.ViewHolder(
        inflater.inflate(
            R.layout.fragment_sort_option_list_dialog_item,
            parent,
            false
        )
    ) {
        internal val sortOption: MaterialButton = itemView.sort_option
    }

    private fun getSortOptions(context: Context): List<String> =
        with(context) {
            listOf(
                getString(R.string.sort_by_cases),
                getString(R.string.sort_by_recovered),
                getString(R.string.sort_by_deaths)
            )
        }

    private inner class StringAdapter internal constructor(val sortOptions: List<String>) :
        RecyclerView.Adapter<ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            return ViewHolder(LayoutInflater.from(parent.context), parent)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.sortOption.text = sortOptions[position]
        }

        override fun getItemCount(): Int {
            return sortOptions.size
        }
    }
}