package com.example.booksapp.view

import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.booksapp.R
import com.example.booksapp.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>() {
    override val layoutId: Int
        get() = R.layout.activity_main

    override fun initView() {
        super.initView()

        NavigationUI.setupWithNavController(binding.navBar, findNavController(R.id.nav_host))
    }
}