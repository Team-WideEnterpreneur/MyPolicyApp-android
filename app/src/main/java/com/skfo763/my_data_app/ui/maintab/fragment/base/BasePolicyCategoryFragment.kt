package com.skfo763.my_data_app.ui.maintab.fragment.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.skfo763.my_data_app.R
import com.skfo763.my_data_app.ui.detail.PolicyDetailActivity
import com.skfo763.my_data_app.ui.maintab.data.PolicyItem
import com.skfo763.storage.xls.XlsStorageManager

abstract class BasePolicyCategoryFragment<B: ViewDataBinding>(
    @LayoutRes private val layoutResId: Int = R.layout.fragment_base_policy_category
) : Fragment(), IBasePolicyView {

    lateinit var binding: B

    override val xlsStorageManager
        get() = XlsStorageManager(requireContext())

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, layoutResId, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun moveOnToPolicyDetailActivity(policyData: PolicyItem) {
        startActivity(PolicyDetailActivity.getIntent(requireContext()))
    }
}