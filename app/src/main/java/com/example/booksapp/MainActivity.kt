package com.example.booksapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.booksapp.databinding.ActivityMainBinding
import com.example.booksapp.view.BaseActivity

class MainActivity : BaseActivity<ActivityMainBinding>() {
    override val layoutId: Int
        get() = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun initView() {
        super.initView()

        NavigationUI.setupWithNavController(binding.navBar, findNavController(R.id.nav_host))
    }
}