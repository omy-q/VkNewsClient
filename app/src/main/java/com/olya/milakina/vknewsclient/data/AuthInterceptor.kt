package com.olya.milakina.vknewsclient.data

import com.olya.milakina.vknewsclient.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

internal object AuthInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder().apply {
            addHeader(AUTH_HEADER_NAME, BuildConfig.X_API_KEY)
        }
        return chain.proceed(builder.build())
    }

    private const val AUTH_HEADER_NAME = "X-Api-Key"
}
