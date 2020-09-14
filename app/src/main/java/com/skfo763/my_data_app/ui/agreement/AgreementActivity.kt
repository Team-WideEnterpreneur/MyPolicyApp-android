package com.skfo763.my_data_app.ui.agreement

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.skfo763.my_data_app.R
import com.skfo763.my_data_app.databinding.ActivityAgreementBinding

class AgreementActivity : AppCompatActivity(), IAgreementView {

    companion object {
        private const val URL = "url"

        @JvmStatic
        fun getIntent(context: Context, url: String) = Intent(context, AgreementActivity::class.java).apply {
            putExtra(URL, url)
        }
    }

    private lateinit var binding: ActivityAgreementBinding

    private val viewModel by viewModels<AgreementViewModel> { AgreementViewModel.Factory(this) }

    override val url: String get() = intent.getStringExtra(URL) ?: ""

    override val rowBackGround: Pair<Int, Int>
        get() = R.drawable.bg_border_line_000000 to R.drawable.bg_border_line_000000_00afff

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_agreement)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        binding.viewAgreementSsn.viewModel = viewModel
        binding.viewAgreementSsn.lifecycleOwner = this
        binding.viewAgreementCert.viewModel = viewModel
        binding.viewAgreementCert.lifecycleOwner = this

        observeLiveData()
    }

    private fun observeLiveData() {
        binding.viewAgreementCert.viewAgreementTestCert.setOnClickListener {
            viewModel.onRowClicked(it)
        }

        binding.viewAgreementCert.publicCertPasswordConfirm.setOnClickListener {
            viewModel.onCertifyClicked()
        }

        viewModel.isLoading.observe(this) {
            binding.root.isEnabled = !it
        }
    }

    override fun openAgreementBrowser(webUri: Uri) {
        startActivity(Intent(Intent.ACTION_VIEW, webUri))
    }

    override fun finishActivity() {
        finish()
    }

}