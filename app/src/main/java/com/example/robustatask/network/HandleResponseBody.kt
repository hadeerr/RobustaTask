package com.example.robustatask.network

import okhttp3.ResponseBody

sealed class HandleResponseBody<out T> {
    data class Success<out T>(val value: T) : HandleResponseBody<T>()
    data class Failure(
        val isNetworkError: Boolean,
        val errorCode: Int?,
        val errorBody: ResponseBody?
    ) : HandleResponseBody<Nothing>()
    object Loading : HandleResponseBody<Nothing>()
}