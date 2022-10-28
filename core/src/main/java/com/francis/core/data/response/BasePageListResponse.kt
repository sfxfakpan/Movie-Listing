package com.francis.core.data.response

interface BasePageListResponse<T> {
    var page: Int
    var results: List<T>
}