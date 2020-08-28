package com.skfo763.my_data_app.ui.maintab

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.viewpager2.widget.ViewPager2
import com.skfo763.my_data_app.R
import com.skfo763.my_data_app.databinding.ActivityMainBinding
import com.skfo763.my_data_app.ui.maintab.adapter.MainPagerAdapter

class MainActivity : AppCompatActivity() {

    companion object {
        @JvmStatic
        fun getIntent(context: Context) = Intent(context, MainActivity::class.java)
    }

    private lateinit var binding: ActivityMainBinding
    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        binding.mainTabPager.adapter = MainPagerAdapter(this)
        binding.mainTabLayout.setPager(binding.mainTabPager)

        observeLiveData()
        viewModel.getServiceList()
    }

    private fun observeLiveData() {


    }

    private fun ViewPager2.adapter() = this.adapter as MainPagerAdapter
}