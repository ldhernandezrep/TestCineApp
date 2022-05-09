package com.example.testcineapplication.core

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.testcineapplication.data.remote.Routes
import okhttp3.Route

abstract class BaseViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun bind(item: T, list: List<Routes>)
}