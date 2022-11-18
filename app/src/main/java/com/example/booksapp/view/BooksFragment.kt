package com.example.booksapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.booksapp.R
import com.example.booksapp.databinding.FragmentBooksBinding

class BooksFragment : BaseFragment<FragmentBooksBinding>() {
    override val layoutId: Int
        get() = R.layout.fragment_books

}