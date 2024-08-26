package com.prakhar.jobs.screens.detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.prakhar.jobs.model.JobBookmark
import com.prakhar.jobs.repository.JobsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailScreenViewModel @Inject constructor( private val jobsRepository: JobsRepository) : ViewModel(){

    var isBookmarked: Boolean by mutableStateOf(false)

    fun addJobBookmark(job: JobBookmark) {

        viewModelScope.launch {
            jobsRepository.bookmarkJob(job)
        }

    }

    fun removeJobBookmark(job: JobBookmark) {

        viewModelScope.launch {
           jobsRepository.removeBookmarkJob(job)
        }

    }

     fun checkJobBookmark(id : Int) {

        viewModelScope.launch {

            viewModelScope.launch {
                isBookmarked = jobsRepository.isJobBookmarked(id)
            }

        }

    }
}
