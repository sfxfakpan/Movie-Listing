package com.francis.core.data

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class Cast(
    @SerializedName("id")
    var id: Int,

    @SerializedName("name")
    var name: String,

    @SerializedName("profile_path")
    var profilePath: String?
)