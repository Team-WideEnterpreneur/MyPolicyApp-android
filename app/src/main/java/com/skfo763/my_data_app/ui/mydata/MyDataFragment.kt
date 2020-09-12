package com.skfo763.my_data_app.ui.mydata

import android.app.AlertDialog
import android.content.Intent
import android.graphics.Rect
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.skfo763.my_data_app.R
import com.skfo763.my_data_app.databinding.FragmentMyDataBinding
import com.skfo763.my_data_app.ext.DPInt
import com.skfo763.storage.xls.XlsStorageManager


class MyDataFragment : Fragment(), IMyDataView {

    companion object {
        fun newInstance(): MyDataFragment = MyDataFragment()
    }

    lateinit var binding: FragmentMyDataBinding
    override val xlsStorageManager by lazy { XlsStorageManager(requireContext()) }

    private val viewModel by viewModels<MyDataViewModel> { MyDataViewModel.Factory(this) }

    private val adapterLastPos: Int
        get() = (binding.myDataList.adapter?.itemCount ?: 1) - 1

    private val recyclerViewSpan = object: GridLayoutManager.SpanSizeLookup() {
        override fun getSpanSize(position: Int): Int {
            return when(position) {
                0, 1, adapterLastPos -> 2
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
                pos % 2 == 1 -> outRect.set(
                    4.DPInt(requireContext()),
                    8.DPInt(requireContext()),
                    16.DPInt(requireContext()),
                    8.DPInt(requireContext())
                )
                else -> outRect.set(
                    16.DPInt(requireContext()),
                    8.DPInt(requireContext()),
                    4.DPInt(requireContext()),
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeLiveData()

        viewModel.loadMyData()
    }

    private fun setRecyclerView() {
        binding.myDataList.layoutManager = GridLayoutManager(context, 2).apply {
            spanSizeLookup = recyclerViewSpan
        }
        binding.myDataList.addItemDecoration(recyclerItemDecoration)
    }

    private fun observeLiveData() {
        viewModel.myInfoData.observe(viewLifecycleOwner, {
            AlertDialog.Builder(requireContext())
                .setTitle(R.string.support_file_type_dialog_title)
                .setItems(R.array.support_download_type) { dialog, pos ->
                    when (pos) {
                        0 -> viewModel.saveXls(it.getMyInfoXlsData())
                        1 -> viewModel.saveCsv(it.getMyInfoXlsData())
                        2 -> viewModel.savePdf(it.getMyInfoPdfData())
                        else -> Toast.makeText(
                            requireContext(),
                            "지원하지 않는 파일 포맷입니다.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }.create()
                .show()
        })

        viewModel.fileUri.observe(viewLifecycleOwner, { uri ->
            uri?.first?.let {
                val intent = Intent(Intent.ACTION_SEND).apply {
                    type = uri.second
                    putExtra(Intent.EXTRA_STREAM, it)
                }
                startActivity(Intent.createChooser(intent, "엑셀 내보내기"))
            } ?: kotlin.run {

            }
        })
    }
}