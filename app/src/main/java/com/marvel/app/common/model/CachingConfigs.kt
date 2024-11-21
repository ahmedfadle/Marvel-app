package com.marvel.app.common.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.io.Serializable

@Parcelize
data class CachingConfigs(
    var lastUpdatedDate: String? = null,
    var expirationHours: Long? = null
):Parcelable,Serializable
