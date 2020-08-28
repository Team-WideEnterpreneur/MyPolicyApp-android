package com.skfo763.my_data_app.ui.maintab.data

import com.skfo763.my_data_app.databinding.FragmentBasePolicyCategoryBinding
import com.skfo763.my_data_app.ui.maintab.fragment.Entrepreneur.EntrepreneurPolicyFragment
import com.skfo763.my_data_app.ui.maintab.fragment.all.AllPolicyFragment
import com.skfo763.my_data_app.ui.maintab.fragment.base.BasePolicyCategoryFragment
import com.skfo763.my_data_app.ui.maintab.fragment.housing.HousingPolicyFragment
import com.skfo763.my_data_app.ui.maintab.fragment.job.JobPolicyFragment
import com.skfo763.my_data_app.ui.maintab.fragment.life.Covid19PolicyFragment
import com.skfo763.my_data_app.ui.maintab.fragment.life.LifePolicyFragment
import com.skfo763.my_data_app.ui.maintab.fragment.participate.ParticipatePolicyFragment

/**
 * 현재 지원중인 서비스 리스트를 유연하게 관리하기 위한 클래스입니다.
 * 지원 정책 카테고리가 추가되면 여기에 추가해주세요.
 * ServiceKey 값은 서버에서 내려주는 키 값과 동일해야 합니다.
 */
object MainServiceFragmentList {
    enum class ServiceKey(val key: String) {
        ALL("all"),
        JOB("job"),
        ENTREPRENEURSHIP("entrepreneurship"),
        LIFE("life"),
        HOUSING("housing"),
        PARTICIPATE("participate"),
        COVID_19("covid19"),
        NONE("none")
    }

    private val serviceFragmentList =
        hashMapOf<ServiceKey, BasePolicyCategoryFragment<FragmentBasePolicyCategoryBinding>>().apply {
            set(ServiceKey.ALL, AllPolicyFragment.newInstance())
            set(ServiceKey.JOB, JobPolicyFragment.newInstance())
            set(ServiceKey.ENTREPRENEURSHIP, EntrepreneurPolicyFragment.newInstance())
            set(ServiceKey.LIFE, LifePolicyFragment.newInstance())
            set(ServiceKey.HOUSING, HousingPolicyFragment.newInstance())
            set(ServiceKey.PARTICIPATE, ParticipatePolicyFragment.newInstance())
            set(ServiceKey.COVID_19, Covid19PolicyFragment.newInstance())
        }

    private fun getServiceKey(key: String) =
        when(key) {
            ServiceKey.ALL.key -> ServiceKey.ALL
            ServiceKey.JOB.key -> ServiceKey.JOB
            ServiceKey.LIFE.key -> ServiceKey.LIFE
            ServiceKey.HOUSING.key -> ServiceKey.HOUSING
            ServiceKey.PARTICIPATE.key -> ServiceKey.PARTICIPATE
            ServiceKey.COVID_19.key -> ServiceKey.COVID_19
            else -> ServiceKey.NONE
        }

    fun getFragmentList(serviceList: List<MainServiceType>): List<Pair<MainServiceType, BasePolicyCategoryFragment<FragmentBasePolicyCategoryBinding>>> {
        return serviceList.map {
            val key = getServiceKey(it.tabKey)
            it to serviceFragmentList[key]
        }.filter {
            it.second != null
        }.map {
            it.first to it.second!!
        }
    }
}