package com.marvel.app.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CharacterResponse(
    val status: String?,
    val code: Int?,
    val data: CharacterData?,
) : Parcelable