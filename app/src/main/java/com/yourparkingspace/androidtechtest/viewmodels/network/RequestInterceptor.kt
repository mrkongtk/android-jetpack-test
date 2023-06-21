package com.yourparkingspace.androidtechtest.viewmodels.network

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response

/**
 * The network interceptor used to intercept and modify outgoing requests.
 * This object allows developers to add custom headers or authorization tokens to requests
 * by implementing the appropriate functions here.
 */
object RequestInterceptor : Interceptor {

    private val TAG = RequestInterceptor::class.simpleName

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        Log.d(TAG, "Outgoing request to ${request.url()}")
        val response = chain.proceed(request)
        Log.d(TAG, "response code: ${response.code()}")
        return response
    }
}