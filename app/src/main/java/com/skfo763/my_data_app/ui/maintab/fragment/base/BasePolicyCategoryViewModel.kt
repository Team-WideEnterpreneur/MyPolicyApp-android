package com.skfo763.my_data_app.ui.maintab.fragment.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.skfo763.my_data_app.component.PolicyLabelView
import com.skfo763.my_data_app.extension.plusAssign
import com.skfo763.my_data_app.getRandomLabelType
import com.skfo763.my_data_app.makeUserData
import com.skfo763.my_data_app.ui.maintab.data.PolicyHeader
import com.skfo763.my_data_app.ui.maintab.data.PolicyItem
import com.skfo763.my_data_app.ui.maintab.data.PolicyListItem
import com.skfo763.my_data_app.ui.maintab.data.PolicySearchBar
import com.skfo763.repository.PolicyRepository
import com.skfo763.repository.UserRepository
import com.skfo763.repository.data.PolicyData
import com.skfo763.repository.data.PolicySummaryData
import com.skfo763.repository.data.UserData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import kotlin.random.Random

abstract class BasePolicyCategoryViewModel(
    private val view: IBasePolicyView,
    private val userRepository: UserRepository = UserRepository(view.xlsStorageManager),
    private val policyRepository: PolicyRepository = PolicyRepository(view.xlsStorageManager)
): ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    abstract val categoryName: String

    private val _recyclerItemList = MutableLiveData(mutableListOf<PolicyListItem>())

    val recyclerItemList: LiveData<MutableList<PolicyListItem>> = _recyclerItemList

    /*
    fun getPolicyListData() {
        compositeDisposable += policyRepository.getPolicySummaryList()
            .zipWith(userRepository.getUserInfo(0)) { policyList, user ->
                user to policyList
            }.observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _recyclerItemList.value = getRecyclerItemList(it)
            }) {
                it.printStackTrace()
            }
    }
     */

    fun getPolicyListData() {
        compositeDisposable += policyRepository.getPolicySummaryList()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _recyclerItemList.value = getRecyclerItemList(makeUserData() to it)
            }) {
                it.printStackTrace()
            }
    }

    private fun getRecyclerItemList(data: Pair<UserData, List<PolicyData>>): MutableList<PolicyListItem> {
        return mutableListOf<PolicyListItem>().apply {
            // 헤더
            add(PolicyHeader(
                categoryName,
                data.first.name,
                "님이 지원가능한 ${categoryName} 정책은",
                data.second.size,
                view.headerBackgroundDrawable
            ))

            // 검색창
            add(PolicySearchBar(
                "검색어를 입력해주세요",
                view.searchSeedList
            ))

            // 정책 데이터
            addAll(data.second.map { PolicyItem(
                it.policyName, "간단한 정책 설명", getRandomLabelType(),
                onClick = { item -> onRecyclerViewItemClicked(item) }
            ) } )
        }
    }

    private fun onRecyclerViewItemClicked(policyData: PolicyItem) {
        view.moveOnToPolicyDetailActivity(policyData)
    }

}