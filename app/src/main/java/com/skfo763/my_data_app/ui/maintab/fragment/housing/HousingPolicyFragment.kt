package com.skfo763.my_data_app.ui.maintab.fragment.housing

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.skfo763.my_data_app.R
import com.skfo763.my_data_app.databinding.FragmentBasePolicyCategoryBinding
import com.skfo763.my_data_app.ui.maintab.fragment.base.BasePolicyCategoryFragment

class HousingPolicyFragment : BasePolicyCategoryFragment<FragmentBasePolicyCategoryBinding>() {

    companion object {
        @JvmStatic
        fun newInstance() = HousingPolicyFragment()
    }

    override val headerBackgroundDrawable: Drawable
        get() = ContextCompat.getDrawable(requireContext(), R.drawable.gradient_splash) ?: ColorDrawable(Color.TRANSPARENT)

    override val searchSeedList: List<String>
        get() = resources.getStringArray(R.array.policy_search_seed).toList()

    val viewModel by viewModels<HousingPolicyViewModel>{ HousingPolicyViewModelFactory(this) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        binding.viewModel = viewModel
        binding.mainPolicyList.layoutManager = LinearLayoutManager(context)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getPolicyListData()
    }

}