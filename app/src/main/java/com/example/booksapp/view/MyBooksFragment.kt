package com.example.booksapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.booksapp.R
import com.example.booksapp.databinding.FragmentMyBooksBinding

class MyBooksFragment : BaseFragment<FragmentMyBooksBinding>() {
    override val layoutId: Int
        get() = R.layout.fragment_my_books
}