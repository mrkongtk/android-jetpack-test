package com.yourparkingspace.androidtechtest.viewmodels.viewassistant

import com.yourparkingspace.androidtechtest.BuildConfig
import com.yourparkingspace.androidtechtest.models.GetHotSubmissionResponseData
import com.yourparkingspace.androidtechtest.models.HotSubmissionListViewData
import com.yourparkingspace.androidtechtest.models.SubmissionViewData
import com.yourparkingspace.androidtechtest.viewmodels.network.FetchHotSubmissions
import io.reactivex.rxjava3.core.Observable

/**
 * This object acts as a middleman between the network component and the UI component.
 * It provides a method to execute the retrieval of hot submissions and converts the response data
 * into a format suitable for the UI.
 */
object GetHotSubmissions {

    /**
     * Executes the retrieval of hot submissions.
     * @param after The identifier of the submission to start retrieving after (optional).
     * @return An Observable emitting the converted hot submission data suitable for the UI.
     */
    fun execute(after: String? = null): Observable<HotSubmissionListViewData> {
        return FetchHotSubmissions
            .execute(after)
            .map{x -> convert(x)}
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
                    BuildConfig.BASE_URL + y.data.permalink
                )
            })
    }
}