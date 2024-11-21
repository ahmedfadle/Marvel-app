package com.marvel.app.domain.models.comics

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Url(
    val type: String?,
    val url: String?
):Parcelable