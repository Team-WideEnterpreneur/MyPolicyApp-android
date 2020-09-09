package com.skfo763.my_data_app.ui.maintab

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import androidx.activity.viewModels
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.viewpager2.widget.ViewPager2
import com.skfo763.my_data_app.R
import com.skfo763.my_data_app.databinding.ActivityMainBinding
import com.skfo763.my_data_app.ui.maintab.adapter.MainPagerAdapter
import com.skfo763.my_data_app.ui.mydata.MyDataFragment

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

        setMyDataFragment()
        observeLiveData()
        viewModel.getServiceList()
    }

    private fun setMyDataFragment() {
        binding.mainMotionLayout.addTransitionListener(object: MotionLayout.TransitionListener {
            override fun onTransitionStarted(p0: MotionLayout?, p1: Int, p2: Int) {
            }

            override fun onTransitionChange(p0: MotionLayout?, p1: Int, p2: Int, p3: Float) {
            }

            override fun onTransitionCompleted(p0: MotionLayout?, p1: Int) {
                when(p1) {
                    R.id.open_my_end -> viewModel.openMyData = true
                    R.id.open_my_start -> viewModel.openMyData = false
                }
            }

            override fun onTransitionTrigger(p0: MotionLayout?, p1: Int, p2: Boolean, p3: Float) {
            }
        })

        supportFragmentManager.commit {
            add(R.id.main_tab_my_data, MyDataFragment.newInstance())
        }
    }

    private fun observeLiveData() {


    }

    override fun onBackPressed() {
        if(viewModel.openMyData) {
            binding.mainMotionLayout.transitionToState(R.id.open_my_start)
        } else {
            super.onBackPressed()
        }
    }

    private fun ViewPager2.adapter() = this.adapter as MainPagerAdapter
}