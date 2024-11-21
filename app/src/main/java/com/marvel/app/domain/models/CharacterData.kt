package com.marvel.app.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CharacterData(
    val count: Int?,
    val limit: Int?,
    val offset: Int?,
    val results: List<CharacterResult>?,
    val total: Int?
):Parcelable