package com.gilson.githubviewer.data.network.interceptors

import okhttp3.Interceptor
import okhttp3.Response

class RequestHeaderInterceptor : Interceptor {
    private val HEADER_ACCEPT = "Accept"

    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder()
        builder.addHeader(HEADER_ACCEPT, "application/vnd.github.v3+json")
        return chain.proceed(builder.build())
    }
}