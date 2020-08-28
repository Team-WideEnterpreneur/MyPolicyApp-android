package com.skfo763.my_data_app.ui.maintab.fragment.life

import com.skfo763.my_data_app.component.PolicyLabelView
import com.skfo763.my_data_app.ui.maintab.data.PolicyHeader
import com.skfo763.my_data_app.ui.maintab.data.PolicyItem
import com.skfo763.my_data_app.ui.maintab.data.PolicyListItem
import com.skfo763.my_data_app.ui.maintab.data.PolicySearchBar
import com.skfo763.my_data_app.ui.maintab.fragment.base.BasePolicyCategoryViewModel
import com.skfo763.my_data_app.ui.maintab.fragment.base.IBasePolicyView

class Covid19PolicyViewModel(private val view: IBasePolicyView) : BasePolicyCategoryViewModel(view) {
    override val tempRecyclerItemList: MutableList<PolicyListItem> = mutableListOf(
        PolicyHeader("코로나19", "김OO님이 지원 가능한 정책은", 25, view.headerBackgroundDrawable),
        PolicySearchBar("검색어를 입력해주세요", view.searchSeedList),
        PolicyItem("청년내일채움공제(2020년)", "중소, 중견기업 청년 근로자의 장기근속과 자산형성 지원", PolicyLabelView.LabelType.TYPE_ON_AIR, onClick = this::onRecyclerViewItemClicked),
        PolicyItem("청년내일채움공제(2020년)", "중소, 중견기업 청년 근로자의 장기근속과 자산형성 지원", PolicyLabelView.LabelType.TYPE_ON_AIR, onClick = this::onRecyclerViewItemClicked),
        PolicyItem("청년내일채움공제(2020년)", "중소, 중견기업 청년 근로자의 장기근속과 자산형성 지원", PolicyLabelView.LabelType.TYPE_ON_AIR, onClick = this::onRecyclerViewItemClicked),
        PolicyItem("청년내일채움공제(2020년)", "중소, 중견기업 청년 근로자의 장기근속과 자산형성 지원", PolicyLabelView.LabelType.TYPE_ON_AIR, onClick = this::onRecyclerViewItemClicked),
        PolicyItem("청년내일채움공제(2020년)", "중소, 중견기업 청년 근로자의 장기근속과 자산형성 지원", PolicyLabelView.LabelType.TYPE_ON_AIR, onClick = this::onRecyclerViewItemClicked),
        PolicyItem("청년내일채움공제(2020년)", "중소, 중견기업 청년 근로자의 장기근속과 자산형성 지원", PolicyLabelView.LabelType.TYPE_ON_AIR, onClick = this::onRecyclerViewItemClicked),
        PolicyItem("청년내일채움공제(2020년)", "중소, 중견기업 청년 근로자의 장기근속과 자산형성 지원", PolicyLabelView.LabelType.TYPE_ON_AIR, onClick = this::onRecyclerViewItemClicked),
        PolicyItem("청년내일채움공제(2020년)", "중소, 중견기업 청년 근로자의 장기근속과 자산형성 지원", PolicyLabelView.LabelType.TYPE_ON_AIR, onClick = this::onRecyclerViewItemClicked),
    )





}