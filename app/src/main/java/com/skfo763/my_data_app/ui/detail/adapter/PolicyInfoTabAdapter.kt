package com.skfo763.my_data_app.ui.detail.adapter

import android.view.ViewGroup
import com.skfo763.my_data_app.base.BaseRecyclerAdapter
import com.skfo763.my_data_app.base.BaseViewHolder
import com.skfo763.my_data_app.ui.detail.data.PolicyDetailInfo
import com.skfo763.my_data_app.ui.detail.data.PolicyDetailTabData
import com.skfo763.my_data_app.ui.detail.data.PolicyRequirement
import java.lang.IllegalStateException

class PolicyInfoTabAdapter: BaseRecyclerAdapter<PolicyDetailTabData>() {

    enum class ViewType(val type: Int) {
        TYPE_DETAIL(0),
        TYPE_REQUIREMENT(1)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<PolicyDetailTabData> {
        val holder = when(viewType) {
            ViewType.TYPE_DETAIL.type -> DetailInfoViewHolder.instance(parent, viewType)
            ViewType.TYPE_REQUIREMENT.type -> RequirementViewHolder.instance(parent, viewType)
            else -> throw IllegalStateException("wrong view type")
        }

        return holder
    }

    override fun onBindViewHolder(holder: BaseViewHolder<PolicyDetailTabData>, position: Int) {
        holder.setData(dataList[position])
    }

    override fun getItemViewType(position: Int) = when(dataList[position]) {
        is PolicyDetailInfo -> ViewType.TYPE_DETAIL.type
        is PolicyRequirement -> ViewType.TYPE_REQUIREMENT.type
    }

}