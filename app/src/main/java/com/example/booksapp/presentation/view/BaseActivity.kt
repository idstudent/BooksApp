package com.example.booksapp.presentation.view

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseActivity<BINDING : ViewDataBinding> : AppCompatActivity() {
    protected lateinit var binding: BINDING
    abstract val layoutId: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        binding = DataBindingUtil.setContentView(this, layoutId)

        initView()
        initViewModel()
        initListener()
    }

    open fun initView() {}
    open fun initViewModel() {}
    open fun initListener() {}
}