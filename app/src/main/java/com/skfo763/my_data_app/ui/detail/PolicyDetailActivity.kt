package com.skfo763.my_data_app.ui.detail

import android.content.Context
import android.content.Intent
import android.content.Intent.ACTION_VIEW
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.viewpager2.widget.ViewPager2
import com.skfo763.my_data_app.R
import com.skfo763.my_data_app.databinding.ActivityPolicyDetailBinding
import com.skfo763.my_data_app.ext.parseWithNullString
import com.skfo763.my_data_app.ui.detail.adapter.PolicyInfoTabAdapter

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
        setPager()
        observeLiveData()

        viewModel.initTabData()
        viewModel.getPolicyMatchingInfo()
    }

    private fun observeLiveData() {
        viewModel.registerUrl.observe(this) { url ->
            startActivity(Intent(ACTION_VIEW, url.parseWithNullString()))
        }

        viewModel.alertDialogTitle.observe(this) { title ->
            AlertDialog.Builder(this)
                .setTitle(title)
                .setPositiveButton("확인") { dialog, i ->
                    dialog.dismiss()
                }.create()
                .show()
        }
    }

    private fun setPager() {
        binding.policyDetailInfoView.policyInfoTabLayout.setPager(binding.policyDetailInfoView.policyInfoPager)
        binding.policyDetailInfoView.policyInfoPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        binding.policyDetailInfoView.policyInfoPager.adapter = PolicyInfoTabAdapter()
    }

    override fun getArrayListRes(resId: Int) = resources.getStringArray(resId).toList()

}