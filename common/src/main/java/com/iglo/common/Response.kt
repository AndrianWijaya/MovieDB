package com.iglo.common

import okhttp3.ResponseBody

sealed class ResponseApps<T>(
    val condition: Int = 2,
    val data: T?,
    val error: Exception?,
    val code: Int?,
    val errorBody: ResponseBody?
) {
    companion object{
        const val ERROR = 0
        const val SUCCESS = 1
        const val LOADING = 2

        fun <T> success(t: T): ResponseApps<T> = ApplicationResponseSuccess(t)
        fun <T> errorBackend(responseCode: Int, body: ResponseBody?): ResponseApps<T> =
            ApplicationResponseError(null, responseCode, body)
        fun <T> loading(): ResponseApps<T> = ApplicationResponseLoading()
    }
}

class ApplicationResponseLoading<T>: ResponseApps<T>(2, null, null, null, null)

class ApplicationResponseSuccess<T>(
    data: T?
): ResponseApps<T>(1, data, null, null, null)

class ApplicationResponseError<T>(
    exception: Exception?,
    code: Int?,
    responseBody: ResponseBody?
): ResponseApps<T>(0, null, exception, code, responseBody) {
    companion object{
        const val ERROR_SYSTEM = -1
    }
}

