package com.skfo763.my_data_app.ui.mydata.data

import android.widget.CompoundButton
import com.dino.library.dinorecyclerview.ItemViewType
import com.skfo763.my_data_app.R
import com.skfo763.my_data_app.commondata.GenderEnum
import com.skfo763.my_data_app.commondata.IncomeState
import com.skfo763.my_data_app.commondata.JobState
import com.skfo763.my_data_app.component.PolicyLabelView

sealed class MyDataListItem: ItemViewType

class MyDataHeader(
    val title: String = "000님의 정책 신청/열람 내역",
    countFormatString: String = "총 %d회",
    count: Int = 15,
    val firstDescription: String = "신청 및 열람하신 정책 내역입니다.",
    val secondDescription: String = "다운로드 버튼을 눌러 소득, 건강보험료 정보를 다운로드하세요!",
    val onDownloadBtnClicked: (MyDataHeader) -> Unit = {},
    val onSwitchChanged: (CompoundButton, Boolean) -> Unit = {_, _-> },
    val myInfoData: MyInfoData = MyInfoData("서창연", 23, GenderEnum.MAN, JobState.EMPLOYED, "(주)모젯", IncomeState.TENTH_QUARTILE),
    override val itemLayoutResId: Int = R.layout.item_my_data_header
) : MyDataListItem() {
    val countString= String.format(countFormatString, count)
}

class MyDataTitle(
    override val itemLayoutResId: Int = R.layout.item_my_data_list_title
): MyDataListItem()

class MyDataItem(
    val category: String = "주거복지",
    val title: String = "중소기업 취업 청년 전세자금대출",
    val labelType: PolicyLabelView.LabelType = PolicyLabelView.LabelType.TYPE_ON_AIR,
    val onItemClicked: (MyDataItem) -> Unit = {},
    override val itemLayoutResId: Int = R.layout.item_my_data_listitem
) : MyDataListItem()

class MyDataFooter(
    override val itemLayoutResId: Int = R.layout.item_my_data_footer
) : MyDataListItem()