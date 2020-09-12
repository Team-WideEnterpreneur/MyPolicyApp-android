package com.skfo763.my_data_app.ui.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.skfo763.my_data_app.R
import com.skfo763.my_data_app.databinding.ActivityPolicyDetailBinding
import com.skfo763.storage.xls.XlsMyInfoData
import com.skfo763.storage.xls.XlsStorageManager

class PolicyDetailActivity : AppCompatActivity(), IPolicyDetailView {

    companion object {
        @JvmStatic
        fun getIntent(context: Context) = Intent(context, PolicyDetailActivity::class.java)
    }

    private lateinit var binding: ActivityPolicyDetailBinding
    private val viewModel by viewModels<PolicyDetailViewModel> {
        PolicyDetailViewModel.Factory(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_policy_detail)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        binding.policyDetailInfoView.viewModel = viewModel
        binding.policyDetailInfoView.lifecycleOwner = this

        setSupportActionBar(binding.policyDetailToolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }

    override fun getArrayListRes(resId: Int) = resources.getStringArray(resId).toList()

}