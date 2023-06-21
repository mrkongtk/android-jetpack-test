package com.yourparkingspace.androidtechtest.viewmodels.network

import com.yourparkingspace.androidtechtest.BuildConfig
import com.yourparkingspace.androidtechtest.models.GetHotSubmissionResponseData
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

object GetHotSubmissions {

    interface Api {
        @GET("r/technology/hot.json")
        fun getContent(
            @Query("after") after: String? = null,
            @Query("limit") limit: Int = 10,)
        : Observable<GetHotSubmissionResponseData>
    }

    private val requestInterface = Retrofit.Builder()
        .client(HttpRequestClient.getClient())
        .baseUrl(BuildConfig.BASE_URL)
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build().create(Api::class.java)

    fun execute(after: String?=null): Observable<GetHotSubmissionResponseData> {
        return requestInterface.getContent(after)
            .subscribeOn(Schedulers.io())
    }
}

