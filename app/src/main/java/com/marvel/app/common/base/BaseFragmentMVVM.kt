package com.marvel.app.common.base

import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding

abstract class BaseFragmentMVVM<VB : ViewBinding, VM : ViewModel>() : BaseFragment<VB>() {

    protected lateinit var viewModel: Lazy<VM>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = initViewModel()

    }
    override fun onResume() {
        super.onResume()
        onCreateInit()
    }

    abstract fun initViewModel(): Lazy<VM>

    abstract fun onCreateInit()
    fun getInitViewModel() = viewModel.value

}