package com.marvel.app.common.base

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding


abstract class BaseActivity<VB : ViewBinding> : AppCompatActivity() {

    protected lateinit var binding: VB


    override fun onCreate(savedInstanceState: Bundle?) {
        onPreSetContentView()// for in case of animation
        super.onCreate(savedInstanceState)
        binding = getViewBinding()
        setContentView(binding.root)

        initOnCreate()

    }

    abstract fun getViewBinding(): VB





    override fun onResume() {
        super.onResume()

    }

    open fun onPreSetContentView() {}
    protected abstract fun initOnCreate()

    fun addFragment(
        @IdRes containerViewId: Int,
        fragment: Fragment,
         fragmentTag: String?
    ) {
        supportFragmentManager
            .beginTransaction()
            .add(containerViewId, fragment, fragmentTag)
            .disallowAddToBackStack()
            .commit()
    }
}