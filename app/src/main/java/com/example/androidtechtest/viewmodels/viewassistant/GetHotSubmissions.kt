package com.example.androidtechtest.viewmodels.viewassistant

import com.example.androidtechtest.models.GetHotSubmissionResponseData
import com.example.androidtechtest.models.HotSubmissionListViewData
import com.example.androidtechtest.models.SubmissionViewData
import com.example.androidtechtest.viewmodels.network.FetchHotSubmissions
import io.reactivex.rxjava3.core.Observable

/**
 * This object acts as a middleman between the network component and the UI component.
 * It provides a method to execute the retrieval of hot submissions and converts the response data
 * into a format suitable for the UI.
 */
class GetHotSubmissions(private val baseUrl: String) {

    private val hotSubmissionsFetcher by lazy { FetchHotSubmissions(baseUrl) }

    /**
     * Executes the retrieval of hot submissions.
     * @param after The identifier of the submission to start retrieving after (optional).
     * @return An Observable emitting the converted hot submission data suitable for the UI.
     */
    fun execute(after: String? = null): Observable<HotSubmissionListViewData> {
        return hotSubmissionsFetcher
            .execute(after)
            .map { x -> convert(x) }
    }

    /**
     * Converts the response data into a format suitable for the UI.
     * @param data The response data of the hot submissions.
     * @return The converted hot submission data suitable for the UI.
     */
    private fun convert(data: GetHotSubmissionResponseData): HotSubmissionListViewData {
        return HotSubmissionListViewData(data.data.after,
            data.data.children.map { y ->
                SubmissionViewData(
                    y.data.id,
                    y.data.title,
                    y.data.authorFullName,
                    baseUrl + y.data.permalink
                )
            })
    }
}