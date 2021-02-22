package com.vn.fsoft.gstlib.core.network.api

import androidx.annotation.IntDef
import com.vn.fsoft.gstlib.core.network.api.ResponseStatus.Companion.ERROR
import com.vn.fsoft.gstlib.core.network.api.ResponseStatus.Companion.LOADING
import com.vn.fsoft.gstlib.core.network.api.ResponseStatus.Companion.SUCCESS


/**
 * Status of a resource that is provided to the UI.
 *
 *
 * These are usually created by the Repository classes where they return
 * `LiveData<Resource<T>>` to pass back the latest data to the UI with its fetch status.
 */
/**
 * Avoid Enum in android
 * Need use IntDef or StringDef
 */
//enum class Status {
//    SUCCESS,
//    ERROR,
//    LOADING;
//
//    /**
//     * Returns `true` if the [Status] is success else `false`.
//     */
//    fun isSuccessful() = this == SUCCESS
//
//    /**
//     * Returns `true` if the [Status] is loading else `false`.
//     */
//    fun isLoading() = this == LOADING
//
//    /**
//     * Returns `true` if the [Status] is in error else `false`.
//     */
//    fun isError() = this == ERROR
//}

@IntDef(SUCCESS, ERROR, LOADING)
@Retention(AnnotationRetention.SOURCE)
annotation class ResponseStatus {
    companion object {
        const val SUCCESS = 0
        const val ERROR = 1
        const val LOADING = 2
    }
}

fun Int.isSuccessful(): Boolean {
    return this == SUCCESS
}

fun Int.isLoading(): Boolean {
    return this == LOADING
}

fun Int.isError(): Boolean {
    return this == ERROR
}