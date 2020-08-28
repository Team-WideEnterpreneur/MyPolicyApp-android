package com.skfo763.my_data_app.ui.splash

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.skfo763.my_data_app.R
import com.skfo763.my_data_app.databinding.ActivitySplashBinding
import com.skfo763.my_data_app.ui.maintab.MainActivity

class SplashActivity : AppCompatActivity(), ISplashView {

    lateinit var binding: ActivitySplashBinding
    private val viewModel: SplashViewModel by viewModels { SplashViewModelFactory(this) }

    override val splashConstantText by lazy {
        ISplashView.Text(
            getString(R.string.splash_app_today_service_info),
            getString(R.string.input_user_personal_input_title),
            getString(R.string.input_user_optional_input_title),
            getString(R.string.mandate_input),
            getString(R.string.optional_input)
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.viewSplash.viewModel = viewModel
        binding.viewInputUserInfo.viewModel = viewModel

        observeLiveData()
        viewModel.initializeApplication()
    }

    private fun observeLiveData() {
        viewModel.suppressLayout.observe(this, {
            binding.root.isEnabled = !it
        })
    }

    override fun moveOnToMainActivity() {
        startActivity(MainActivity.getIntent(this))
        this.finish()
    }
}