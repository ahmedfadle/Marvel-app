package com.marvel.app.common.di

import com.bumptech.glide.Glide
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.module.AppGlideModule
import okhttp3.OkHttpClient
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader
import com.marvel.app.common.utils.createUnsafeOkHttpClient
import java.io.InputStream

@GlideModule
class UnsafeGlideModule : AppGlideModule() {
    override fun applyOptions(context: android.content.Context, builder: GlideBuilder) {
        super.applyOptions(context, builder)
    }

    override fun registerComponents(
        context: android.content.Context,
        glide: Glide,
        registry: com.bumptech.glide.Registry
    ) {
        val client = createUnsafeOkHttpClient()
        registry.replace(
            com.bumptech.glide.load.model.GlideUrl::class.java,
            InputStream::class.java,
            OkHttpUrlLoader.Factory(client)
        )
    }
}
