package com.example.booksapp.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import com.example.booksapp.R
import com.example.booksapp.databinding.BottomFragmentSearchFilterBinding
import com.example.booksapp.presentation.view.util.setOnSingleClickListener
import com.example.booksapp.presentation.viewmodel.SearchBookViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class SearchFilterBottomFragment : BottomSheetDialogFragment() {
    private lateinit var binding: BottomFragmentSearchFilterBinding
    private val searchBookViewModel : SearchBookViewModel by activityViewModels()


    override fun getTheme(): Int {
        return R.style.AppBottomSheetDialogTheme
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.AppBottomSheetDialogTheme)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.bottom_fragment_search_filter,
            container,
            false
        )
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.run {
            ivClose.setOnSingleClickListener {
                dismiss()
            }

            tvSearchTitle.setOnSingleClickListener {
                setClickFilterType("title")
            }

            tvSearchAuthor.setOnSingleClickListener {
                setClickFilterType("author")
            }

            tvSearchPublisher.setOnSingleClickListener {
                setClickFilterType("publisher")
            }
        }
    }

    private fun setClickFilterType(filter : String) {
        searchBookViewModel.setFilter(filter)
        dismiss()
    }
}