package com.skfo763.my_data_app.ui.maintab.data

import android.graphics.Color
import android.graphics.drawable.Drawable
import android.text.SpannableStringBuilder
import androidx.annotation.ColorInt
import com.dino.library.dinorecyclerview.ItemViewType
import com.skfo763.my_data_app.R
import com.skfo763.my_data_app.component.PolicyLabelView
import com.skfo763.my_data_app.ext.setSizeSpan
import com.skfo763.repository.data.PolicySummaryData
import com.skfo763.repository.data.UserData

sealed class PolicyListItem: ItemViewType

data class PolicyHeader(
    val policyCategory: String,
    val userName: String,
    val headerStringFooter: String,
    private val availablePolicyCount: Int,
    val headerBackground: Drawable,
    @ColorInt val headerTextColor: Int = Color.WHITE,
    val countStringFooter: String = "입니다.",
    override val itemLayoutResId: Int = R.layout.item_policy_list_header
): PolicyListItem() {
    val headerString: String
        get() = "$userName $headerStringFooter"

    val policyCountString: SpannableStringBuilder
        get() = SpannableStringBuilder("${availablePolicyCount}건 $countStringFooter").apply {
            val footerPoint = "$availablePolicyCount".length + 2 to "$availablePolicyCount".length + 1 + countStringFooter.length
            this.setSizeSpan(0.5f, footerPoint.first, footerPoint.second)
        }
}

data class PolicySearchBar(
    val searchBarHint: String,
    val categorySpinnerList: List<String>,
    override val itemLayoutResId: Int = R.layout.item_policy_list_search
): PolicyListItem()

data class PolicyItem(
    val title: String,
    val description: String,
    val label: PolicyLabelView.LabelType,
    override val itemLayoutResId: Int = R.layout.item_policy_list_policy,
    val onClick: (PolicyItem) -> Unit = { }
): PolicyListItem()