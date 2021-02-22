package com.vn.fsoft.gstlib.core.network.api


/**
 * A generic class that holds a value with its loading status.
 * @param <T>
</T> */
data class Resource<ResultType>(
    @ResponseStatus var status: Int,
    var data: ResultType? = null,
    val exception: Exception? = null
) {

    companion object {
        /**
         * Creates [Resource] object with `SUCCESS` status and [data].
         * Returning object of Resource(Status.SUCCESS, data, null)
         * last value is null so passing it optionally
         *
         */
        fun <ResultType> success(data: ResultType): Resource<ResultType> =
            Resource(ResponseStatus.SUCCESS, data)

        /**
         * Creates [Resource] object with `LOADING` status to notify
         * the UI to showing loading.
         * Returning object of Resource(Status.SUCCESS, null, null)
         * last two values are null so passing them optionally
         */
        fun <ResultType> loading(): Resource<ResultType> =
            Resource(ResponseStatus.LOADING)

        /**
         * Creates [Resource] object with `ERROR` status and [message].
         * Returning object of Resource(Status.ERROR, errorMessage = message)
         */
        fun <ResultType> error(exception: Exception): Resource<ResultType> =
            Resource(
                ResponseStatus.ERROR,
                exception = exception
            )

    }
}