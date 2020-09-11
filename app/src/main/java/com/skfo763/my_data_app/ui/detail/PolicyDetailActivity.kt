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
import com.skfo763.storage.xls.MyPolicyData
import com.skfo763.storage.xls.XlsStorageManager

class PolicyDetailActivity : AppCompatActivity() {

    companion object {
        @JvmStatic
        fun getIntent(context: Context) = Intent(context, PolicyDetailActivity::class.java)
    }

    private lateinit var binding: ActivityPolicyDetailBinding
    private val viewModel by viewModels<PolicyDetailViewModel>()

    private val onTestButtonClicked: (List<MyPolicyData>) -> Unit = {
        val xlsManager = XlsStorageManager(this)
        xlsManager.setSheet("테스트 시트")
        xlsManager.setRow(it, "테스트 시트")
        val uri = xlsManager.saveExcel("file_test.xls")

        uri?.let {
            val intent = Intent(Intent.ACTION_SEND).apply {
                type = "application/excel"
                putExtra(Intent.EXTRA_STREAM, it)
            }
            startActivity(Intent.createChooser(intent, "엑셀 내보내기"))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_policy_detail)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        setSupportActionBar(binding.policyDetailToolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)


        viewModel.hello.observe(this, Observer(onTestButtonClicked))
    }

}