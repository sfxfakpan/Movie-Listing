package com.francis.core.data.response

import com.francis.core.data.Cast
import com.google.gson.annotations.SerializedName

data class CreditsResponse(
    @SerializedName("cast")
    override var results: List<Cast>
) : BaseListResponse<Cast>


