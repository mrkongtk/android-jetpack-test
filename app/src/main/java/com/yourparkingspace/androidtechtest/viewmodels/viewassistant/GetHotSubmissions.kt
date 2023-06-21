package com.yourparkingspace.androidtechtest.viewmodels.viewassistant

import com.yourparkingspace.androidtechtest.BuildConfig
import com.yourparkingspace.androidtechtest.models.HotSubmissionListViewData
import com.yourparkingspace.androidtechtest.models.SubmissionViewData
import com.yourparkingspace.androidtechtest.viewmodels.network.FetchHotSubmissions
import io.reactivex.rxjava3.core.Observable

object GetHotSubmissions {
    fun execute(after: String? = null): Observable<HotSubmissionListViewData> {
        return FetchHotSubmissions
            .execute(after)
            .map { x ->
                HotSubmissionListViewData(x.data.after,
                    x.data.children.map { y ->
                        SubmissionViewData(
                            y.data.id,
                            y.data.title,
                            y.data.authorFullName,
                            BuildConfig.BASE_URL + y.data.permalink
                        )
                    })
            }
    }
}