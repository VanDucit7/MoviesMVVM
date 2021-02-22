package com.vn.fsoft.gstlib.core.network

import android.content.Context
import com.vn.fsoft.gstlib.core.extensions.isInternetAvailable
import okhttp3.Interceptor
import okhttp3.Response

class NetworkConnectionInterceptor(
    context: Context
) : Interceptor {

    private val applicationContext = context.applicationContext

    override fun intercept(chain: Interceptor.Chain): Response {
        if (!applicationContext.isInternetAvailable)
            throw NoInternetException("Make sure you have an active data connection")
        return chain.proceed(chain.request())
    }

}