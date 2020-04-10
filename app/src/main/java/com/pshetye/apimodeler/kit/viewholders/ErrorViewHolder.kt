package com.pshetye.apimodeler.kit.viewholders

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.pshetye.apimodeler.R

class ErrorViewHolder internal constructor(
    private val view: View
): RecyclerView.ViewHolder(view) {
    fun bind(errorMessage: String) {
        if (errorMessage.isNotEmpty()) {
            view.findViewById<TextView>(R.id.error_message).text = errorMessage
        }
    }
}

fun getErrorViewHolder(parentView: ViewGroup): ErrorViewHolder {
    with(LayoutInflater.from(parentView.context)) {
        return ErrorViewHolder(
            inflate(R.layout.item_view_error, parentView, false)
        )
    }
}