package com.skfo763.my_data_app.ui.detail

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.skfo763.my_data_app.R
import com.skfo763.my_data_app.databinding.ActivityPolicyDetailBinding
import com.skfo763.my_data_app.ext.afterMeasured
import kotlinx.android.synthetic.main.activity_policy_detail.*

class PolicyDetailActivity : AppCompatActivity() {

    companion object {
        @JvmStatic
        fun getIntent(context: Context) = Intent(context, PolicyDetailActivity::class.java)
    }

    private lateinit var binding: ActivityPolicyDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_policy_detail)
        binding.lifecycleOwner = this

        setSupportActionBar(binding.policyDetailToolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }

}