package com.yourparkingspace.androidtechtest.viewmodels.viewassistant

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import com.yourparkingspace.androidtechtest.models.SubmissionViewData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class SubmissionListViewModel: ViewModel() {

    private var submissionList = mutableStateListOf<SubmissionViewData>()
    private val _submissionListStateFlow = MutableStateFlow(submissionList)

    val listStateFlow: StateFlow<List<SubmissionViewData>> get() = _submissionListStateFlow

    fun addRecords(newRecords: Collection<SubmissionViewData>){
        submissionList.addAll(newRecords)
    }

    // We can retrieve an entire new list
    fun updateAllRecords(newRecords: Collection<SubmissionViewData>) {
        submissionList = newRecords.toMutableStateList()
        _submissionListStateFlow.value = submissionList
    }

}