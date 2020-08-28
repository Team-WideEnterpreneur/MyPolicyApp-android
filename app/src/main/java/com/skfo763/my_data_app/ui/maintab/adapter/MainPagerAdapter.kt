package com.skfo763.my_data_app.ui.maintab.adapter

import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.skfo763.my_data_app.databinding.FragmentBasePolicyCategoryBinding
import com.skfo763.my_data_app.ui.maintab.data.MainServiceType
import com.skfo763.my_data_app.ui.maintab.fragment.base.BasePolicyCategoryFragment

class MainPagerAdapter(
    activity: FragmentActivity
) : FragmentStateAdapter(activity) {

    private val fragmentList = mutableListOf<Pair<MainServiceType, BasePolicyCategoryFragment<FragmentBasePolicyCategoryBinding>>>()
    
    fun setFragmentList(list: List<Pair<MainServiceType, BasePolicyCategoryFragment<FragmentBasePolicyCategoryBinding>>>) {
        fragmentList.clear()
        fragmentList.addAll(list)
        notifyDataSetChanged()
    }

    fun getItem(position: Int) = fragmentList[position]
    
    override fun getItemCount() = fragmentList.size

    override fun createFragment(position: Int) = fragmentList[position].second
    
}