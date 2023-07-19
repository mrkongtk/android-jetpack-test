package com.yourparkingspace.androidtechtest.viewmodels.network

import okhttp3.OkHttpClient

class HttpRequestClient {

    private val requestInterceptor by lazy {
        RequestInterceptor()
    }

    private val okHttpClient by lazy {
        OkHttpClient()
            .newBuilder()
            .addInterceptor(requestInterceptor)
            .build()
    }

    fun getClient(): OkHttpClient {
        return okHttpClient
    }
}