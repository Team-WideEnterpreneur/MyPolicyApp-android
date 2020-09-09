package com.skfo763.my_data_app.ui.mydata

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.skfo763.my_data_app.R
import com.skfo763.my_data_app.databinding.FragmentMyDataBinding

class MyDataFragment : Fragment() {

    companion object {
        fun newInstance(): MyDataFragment = MyDataFragment()
    }


    lateinit var binding: FragmentMyDataBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_my_data, container, false)
        return binding.root
    }

}