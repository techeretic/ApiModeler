package com.pshetye.covid19.ui.countries.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.button.MaterialButton
import com.pshetye.covid19.R
import com.pshetye.covid19.ui.countries.viewmodels.*

class SortOptionFragment : BottomSheetDialogFragment() {

    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_sort_option_list_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val sortOptions = getSortOptions(view.context)
        val list = view.findViewById<RecyclerView>(R.id.list)
        list.layoutManager = LinearLayoutManager(context)
        list.adapter = SortOptionsAdapter(sortOptions)
        list.adapter
    }

    private inner class ViewHolder constructor(
        inflater: LayoutInflater,
        parent: ViewGroup
    ) : RecyclerView.ViewHolder(
        inflater.inflate(
            R.layout.fragment_sort_option_list_dialog_item,
            parent,
            false
        )
    ) {
        val sortOption: MaterialButton = itemView as MaterialButton
    }

    private fun getSortOptions(context: Context): List<SortOption> =
        with(context) {
            listOf(
                SortOption(getString(R.string.sort_by_cases), SORT_OPTION_CASES),
                SortOption(getString(R.string.sort_by_recovered), SORT_OPTION_RECOVERIES),
                SortOption(getString(R.string.sort_by_critical), SORT_OPTION_CRITICAL),
                SortOption(getString(R.string.sort_by_deaths), SORT_OPTION_DEATHS),
                SortOption(getString(R.string.sort_by_alphabets), SORT_OPTION_ALPHABETICAL)
            )
        }

    private inner class SortOptionsAdapter internal constructor(val sortOptions: List<SortOption>) :
        RecyclerView.Adapter<ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            return ViewHolder(LayoutInflater.from(parent.context), parent)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.sortOption.text = sortOptions[position].sortOptionText
            holder.sortOption.setOnClickListener {
                sharedViewModel.sortOptionLiveData.postValue(sortOptions[position].actualSortOption)
                dismiss()
            }
        }

        override fun getItemCount(): Int {
            return sortOptions.size
        }
    }

    private data class SortOption(
        val sortOptionText: String,
        @SortedBy val actualSortOption: String
    )
}