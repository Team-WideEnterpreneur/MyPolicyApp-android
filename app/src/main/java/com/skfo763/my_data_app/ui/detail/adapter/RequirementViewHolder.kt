package com.skfo763.my_data_app.ui.detail.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.skfo763.my_data_app.base.BaseViewHolder
import com.skfo763.my_data_app.ui.detail.data.PolicyRequirement

class RequirementViewHolder(itemView: View) : BaseViewHolder<PolicyRequirement>(itemView) {

    companion object {
        @JvmStatic
        fun instance(parent: ViewGroup, layoutResId: Int) = RequirementViewHolder(
            LayoutInflater.from(parent.context).inflate(layoutResId, parent, false)
        )
    }

    override fun setData(data: PolicyRequirement) {
        TODO("Not yet implemented")
    }

}