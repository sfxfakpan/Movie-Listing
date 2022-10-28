package com.francis.core.data

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class Video(
    @SerializedName("id")
    var id: String,

    @SerializedName("key")
    var key: String,

    @SerializedName("name")
    var name: String,
)