package com.marvel.app.domain.models.comics

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ComicsResponse(
    val status: String?,
    val code: Int?,
    val data: ComicsData?,
) : Parcelable{


}