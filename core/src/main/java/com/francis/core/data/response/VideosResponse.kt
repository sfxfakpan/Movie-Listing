package com.francis.core.data.response

import com.francis.core.data.Video
import com.google.gson.annotations.SerializedName

data class VideosResponse(
    @SerializedName("results")
    override var results: List<Video>
) : BaseListResponse<Video>
