package com.yourparkingspace.androidtechtest.viewmodels.network

import okhttp3.OkHttpClient

object HttpRequestClient {
    private var okHttpClient: OkHttpClient = OkHttpClient()
        .newBuilder()
        .addInterceptor(RequestInterceptor)
        .build()

    fun getClient(): OkHttpClient {
        return okHttpClient
    }
}