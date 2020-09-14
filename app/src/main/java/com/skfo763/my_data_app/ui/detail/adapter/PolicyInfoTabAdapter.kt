package com.skfo763.my_data_app.ui.detail.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.skfo763.my_data_app.base.BaseRecyclerAdapter
import com.skfo763.my_data_app.ui.detail.PolicyDetailViewModel
import com.skfo763.my_data_app.ui.detail.data.PolicyDetailInfo
import com.skfo763.my_data_app.ui.detail.data.PolicyDetailTabData
import com.skfo763.my_data_app.ui.detail.data.PolicyRequirement

class PolicyInfoTabAdapter: BaseRecyclerAdapter<PolicyDetailTabData>() {

    enum class ViewType(val type: Int) {
        TYPE_DETAIL(0),
        TYPE_REQUIREMENT(1)
    }

    fun setTabList(list: List<PolicyDetailTabData>) {
        this.dataList.clear()
        this.dataList.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        return when(viewType) {
            ViewType.TYPE_DETAIL.type -> DetailInfoViewHolder.instance(parent)
            ViewType.TYPE_REQUIREMENT.type -> RequirementViewHolder.instance(parent)
            else -> throw IllegalStateException("wrong view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder) {
            is DetailInfoViewHolder -> holder.setData(dataList[position] as PolicyDetailInfo)
            is RequirementViewHolder -> holder.setData(dataList[position] as PolicyRequirement)
        }
    }

    override fun getItemViewType(position: Int) = when(dataList[position]) {
        is PolicyDetailInfo -> ViewType.TYPE_DETAIL.type
        is PolicyRequirement -> ViewType.TYPE_REQUIREMENT.type
    }

}