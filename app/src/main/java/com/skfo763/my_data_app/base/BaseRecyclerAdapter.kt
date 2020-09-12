package com.skfo763.my_data_app.base

import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecyclerAdapter<T> : RecyclerView.Adapter<BaseViewHolder<T>>() {

    val dataList = mutableListOf<T>()

    override fun getItemCount() = dataList.size
}