package com.skfo763.my_data_app.ui.detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.skfo763.my_data_app.databinding.ItemPolicyDetailInfoBinding
import com.skfo763.my_data_app.databinding.ItemPolicyDetailRequirementBinding
import com.skfo763.my_data_app.ui.detail.data.PolicyDetailInfo
import com.skfo763.my_data_app.ui.detail.data.PolicyDetailTabData
import com.skfo763.my_data_app.ui.detail.data.PolicyRequirement
import kotlin.reflect.KClass

abstract class PolicyViewHolder(binding: ViewDataBinding): RecyclerView.ViewHolder(binding.root) {
    init {
        binding.lifecycleOwner = itemView.context as LifecycleOwner
    }
}

class DetailInfoViewHolder(
    private val binding: ItemPolicyDetailInfoBinding
) : PolicyViewHolder(binding) {
    companion object {
        @JvmStatic
        fun instance(parent: ViewGroup): DetailInfoViewHolder {
            return DetailInfoViewHolder(ItemPolicyDetailInfoBinding.inflate(
                LayoutInflater.from(parent.context), parent, false))
        }
    }

    fun setData(data: PolicyDetailInfo) {
        binding.item = data
    }
}

class RequirementViewHolder(
    private val binding: ItemPolicyDetailRequirementBinding
) : PolicyViewHolder(binding) {
    companion object {
        @JvmStatic
        fun instance(parent: ViewGroup): RequirementViewHolder {
            return RequirementViewHolder(ItemPolicyDetailRequirementBinding.inflate(
                LayoutInflater.from(parent.context), parent, false))
        }
    }

    fun setData(data: PolicyRequirement) {
        binding.item = data
    }
}