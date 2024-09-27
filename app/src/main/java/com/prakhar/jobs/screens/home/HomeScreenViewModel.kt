package com.prakhar.jobs.screens.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.prakhar.jobs.data.JobsPagingSource
import com.prakhar.jobs.model.JobBookmark
import com.prakhar.jobs.model.Result
import com.prakhar.jobs.repository.JobsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(  private val jobsRepository: JobsRepository, private val jobsPagingSource: JobsPagingSource) : ViewModel(){



    private val _jobItemList = MutableStateFlow<List<JobBookmark>>(emptyList())
    val jobItemList = _jobItemList.asStateFlow()

    private val _jobResponse: MutableStateFlow<PagingData<Result>> =
        MutableStateFlow(PagingData.empty())

    var jobResponse = _jobResponse.asStateFlow()
        private set


    init {

        viewModelScope.launch {
            Pager(
                config = PagingConfig(
                    1, enablePlaceholders = true
                )
            ) {
                jobsPagingSource
            }.flow.cachedIn(viewModelScope).collect {
                _jobResponse.value = it
            }
        }

        viewModelScope.launch(Dispatchers.IO) {
            jobsRepository.getBookmarkedJobs().distinctUntilChanged().collect { listOfJobItem ->
                if (listOfJobItem.isEmpty()) {
                    _jobItemList.value = emptyList()
                    Log.d("BOOKMARKED-JOB-ITEM-LIST", ": Empty")
                } else {
                    _jobItemList.value = listOfJobItem
                }
            }
        }

    }

}