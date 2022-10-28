package com.francis.core.data

import android.os.Parcelable
import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
data class Genre(
    @SerializedName("id")
    var id: Int,

    @SerializedName("name")
    var name: String,
) : Parcelable