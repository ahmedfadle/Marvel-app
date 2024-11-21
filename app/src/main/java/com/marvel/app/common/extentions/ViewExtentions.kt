package com.marvel.app.common.extentions

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.marvel.app.R

fun ImageView.loadImage(url:String){
    Glide.with(this.context)
        .load(url).error(R.drawable.ic_auth_error).into(this)

}