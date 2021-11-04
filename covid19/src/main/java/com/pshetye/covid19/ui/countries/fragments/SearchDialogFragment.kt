package com.pshetye.covid19.ui.countries.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.textfield.TextInputEditText
import com.pshetye.covid19.R


// TODO: Customize parameter argument names
const val ARG_ITEM_COUNT = "item_count"

/**
 *
 * A fragment that shows a list of items as a modal bottom sheet.
 *
 * You can show this modal bottom sheet from your activity like this:
 * <pre>
 *    SearchDialogFragment.newInstance(30).show(supportFragmentManager, "dialog")
 * </pre>
 */
class SearchDialogFragment : BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search_dialog_list_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val results = view.findViewById<RecyclerView>(R.id.results)
        val searchBox = view.findViewById<TextInputEditText>(R.id.search_box)
        results.layoutManager = LinearLayoutManager(context)
        results.adapter = StringAdapter(0)
        searchBox.showSoftInputOnFocus = true
        dialog?.window?.setSoftInputMode(SOFT_INPUT_STATE_VISIBLE);
        searchBox.requestFocus()
    }

    private inner class ViewHolder constructor(
        inflater: LayoutInflater,
        parent: ViewGroup
    ) : RecyclerView.ViewHolder(
        inflater.inflate(
            R.layout.fragment_search_dialog_list_dialog_item,
            parent,
            false
        )
    ) {
        val text: TextView = itemView as TextView
    }

    private inner class StringAdapter internal constructor(private val mItemCount: Int) :
        RecyclerView.Adapter<ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            return ViewHolder(LayoutInflater.from(parent.context), parent)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.text.text = position.toString()
        }

        override fun getItemCount(): Int {
            return mItemCount
        }
    }

}