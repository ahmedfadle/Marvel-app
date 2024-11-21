package com.marvel.app.domain.models.comics

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ComicsData(
    val count: Int?,
    val limit: Int?,
    val offset: Int?,
    val results: List<ComicsResult>?,
    val total: Int?
):Parcelable