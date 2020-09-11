package com.skfo763.my_data_app.ui.mydata

import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.skfo763.my_data_app.R
import com.skfo763.my_data_app.databinding.FragmentMyDataBinding
import com.skfo763.my_data_app.ext.DP
import com.skfo763.my_data_app.ext.DPInt

class MyDataFragment : Fragment(), IMyDataView {

    companion object {
        fun newInstance(): MyDataFragment = MyDataFragment()
    }

    lateinit var binding: FragmentMyDataBinding

    private val viewModel by viewModels<MyDataViewModel> { MyDataViewModel.Factory(this) }

    private val adapterLastPos: Int
        get() = (binding.myDataList.adapter?.itemCount ?: 1) - 1

    private val recyclerViewSpan = object: GridLayoutManager.SpanSizeLookup() {
        override fun getSpanSize(position: Int): Int {
            return when(position) {
                0, adapterLastPos -> 2
                else -> 1
            }
        }
    }

    private val recyclerItemDecoration = object: RecyclerView.ItemDecoration() {
        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
        ) {
            val pos = parent.getChildAdapterPosition(view)
            when {
                pos == 0 -> outRect.set(0, 0, 0, 0)
                pos == adapterLastPos -> outRect.set(0, 0, 0, 0)
                pos % 2 == 0 -> outRect.set(
                    8.DPInt(requireContext()),
                    8.DPInt(requireContext()),
                    16.DPInt(requireContext()),
                    8.DPInt(requireContext())
                )
                else -> outRect.set(
                    16.DPInt(requireContext()),
                    8.DPInt(requireContext()),
                    8.DPInt(requireContext()),
                    8.DPInt(requireContext())
                )
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_my_data, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        setRecyclerView()

        return binding.root
    }

    private fun setRecyclerView() {
        binding.myDataList.layoutManager = GridLayoutManager(context, 2).apply {
            spanSizeLookup = recyclerViewSpan
        }
        binding.myDataList.addItemDecoration(recyclerItemDecoration)
    }
}