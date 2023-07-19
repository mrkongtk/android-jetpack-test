package com.example.androidtechtest.viewmodels.network

import com.example.androidtechtest.models.GetHotSubmissionResponseData
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


/**
 * This object provides functionality to fetch hot submissions from the "r/technology" subreddit.
 */
class FetchHotSubmissions (baseUrl: String) {

    /**
     * The API interface for fetching hot submissions
     */
    interface Api {
        /**
         * Retrieves content from the "r/technology/hot.json" endpoint.
         * @param after The identifier of the submission to start retrieving after,
         * it should be given by the last getContent call (optional).
         * @param limit The maximum number of submissions to retrieve (default: 10).
         * @return An Observable emitting the response data of the hot submissions.
         */
        @GET("r/technology/hot.json")
        fun getContent(
            @Query("after") after: String? = null,
            @Query("limit") limit: Int = 10,
        )
                : Observable<GetHotSubmissionResponseData>
    }

    private val httpRequestClient by lazy { HttpRequestClient().getClient() }
    private val adapterFactory by lazy { RxJava3CallAdapterFactory.create() }
    private val gsonConverterFactory by lazy { GsonConverterFactory.create() }

    /**
     * The network connection client used to make API requests.
     * It is created with default settings, allowing for immediate use without the need to recreate it.
     */
    private val requestInterface by lazy {
        Retrofit.Builder()
            .client(httpRequestClient)
            .baseUrl(baseUrl)
            .addCallAdapterFactory(adapterFactory)
            .addConverterFactory(gsonConverterFactory)
            .build().create(Api::class.java)
    }

    /**
     * Executes the request to fetch hot submissions.
     * @param after The identifier of the submission to start retrieving after,
     * it should be given by the last getContent call (optional).
     * @return An Observable emitting the response data of the hot submissions.
     */
    fun execute(after: String? = null): Observable<GetHotSubmissionResponseData> {
        return requestInterface.getContent(after)
            .subscribeOn(Schedulers.io())
    }
}

