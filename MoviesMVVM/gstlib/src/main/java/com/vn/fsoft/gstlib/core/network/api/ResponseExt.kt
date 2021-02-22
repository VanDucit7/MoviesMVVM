package com.vn.fsoft.gstlib.core.network.api

import com.vn.fsoft.gstlib.core.network.ApiException
import retrofit2.Response
import java.io.IOException

inline fun <T, R> requestApi(request: () -> Response<T>, mapping: (T) -> R): Resource<R> {
    return try {
        Resource.loading<T>()
        val response = request()
        val responseCode = response.code()
        val responseMessage = response.message()
        if (response.isSuccessful) {
            Resource.success(mapping(response.body()!!))
        } else {
            Resource.error(
                ApiException(
                    responseCode,
                    responseMessage
                )
            )
        }

    } catch (e: IOException) {
        Resource.error(e)
    }
}

inline fun <T> requestApi(request: () -> Response<T>): Resource<T> {
    return try {
        Resource.loading<T>()
        val response = request()
        val responseCode = response.code()
        val responseMessage = response.message()
        if (response.isSuccessful) {
            Resource.success(response.body()!!)
        } else {
            Resource.error(
                ApiException(
                    responseCode,
                    responseMessage
                )
            )
        }

    } catch (e: IOException) {
        Resource.error(e)
    }
}

