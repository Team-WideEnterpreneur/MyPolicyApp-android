package com.skfo763.my_data_app.component

import android.content.Context
import android.util.AttributeSet
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.skfo763.my_data_app.ui.maintab.data.MainServiceType

class BadgeTabLayout @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0
): TabLayout(context, attributeSet, defStyleAttr) {

    private var pager: ViewPager2? = null

    fun setPager(pager: ViewPager2) {
        this.pager = pager
    }

    fun attachServiceTabLayout(serviceList: List<MainServiceType>) {
        if(serviceList.isEmpty()) return
        pager?.let {
            TabLayoutMediator(this, it) { tab, pos ->
                tab.text = serviceList[pos].tabName
            }.attach()
        }
    }

    fun attachPolicyInfoTabLayout(policyInfo: List<String>) {
        if(policyInfo.isEmpty()) return
        pager?.let {
            TabLayoutMediator(this, it) { tab, pos ->
                tab.text = policyInfo[pos]
            }.attach()
        }
    }
}