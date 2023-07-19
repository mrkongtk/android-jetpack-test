package com.example.androidtechtest.viewmodels.viewassistant

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import com.example.androidtechtest.models.SubmissionViewData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

/**
 * This SubmissionListViewModel class is a helper class that manages the displayed list of SubmissionViewData objects.
 * It provides methods for updating the list and notifying the UI to update accordingly based on the data set changes.
 */
class SubmissionListViewModel: ViewModel() {

    private var submissionList = mutableStateListOf<SubmissionViewData>()
    private val _submissionListStateFlow = MutableStateFlow(submissionList)

    /**
     * The StateFlow representing the current state of the submission list.
     * It provides an immutable view of the list for observing and updating the UI.
     */
    val listStateFlow: StateFlow<List<SubmissionViewData>> get() = _submissionListStateFlow

    /**
     * Adds new records to the submission list.
     * @param newRecords The collection of SubmissionViewData to be added to the list.
     */
    fun addRecords(newRecords: Collection<SubmissionViewData>){
        submissionList.addAll(newRecords)
    }

    /**
     * Updates all records in the submission list with new records.
     * This replaces the existing list with the provided collection of SubmissionViewData.
     * @param newRecords The collection of SubmissionViewData to update the list with.
     */
    fun updateAllRecords(newRecords: Collection<SubmissionViewData>) {
        submissionList = newRecords.toMutableStateList()
        _submissionListStateFlow.value = submissionList
    }

}