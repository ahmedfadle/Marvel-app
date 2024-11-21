package com.marvel.app

import com.marvel.app.R


import com.marvel.app.common.base.BaseActivity
import com.marvel.app.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {


    override fun getViewBinding(): ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)


    override fun initOnCreate() {

    }
}