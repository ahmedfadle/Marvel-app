package com.marvel.app.domain.models.comics

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ComicsResult(

    val description: String?,
    val diamondCode: String?,
    val digitalId: Int?,
    val format: String?,
    val id: Int?,
    val images: List<Image>?,
    val modified: String?,
    val pageCount: Int?,
    val resourceURI: String?,
    val thumbnail: Thumbnail?,
    val title: String?,


):Parcelable