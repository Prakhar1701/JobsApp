package com.prakhar.jobs.screens.home

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prakhar.jobs.data.Resource
import com.prakhar.jobs.model.Result
import com.prakhar.jobs.repository.JobsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(  private val jobsRepository: JobsRepository) : ViewModel(){

    var listOfJob: List<Result> by mutableStateOf(listOf())

    var isLoading: Boolean by mutableStateOf(true)

    var isSuccess: Boolean by mutableStateOf(false)


    init{
        getJobs()
    }

    private fun getJobs() {

        viewModelScope.launch {

            try {
                when (val jobs = jobsRepository.getJobs()) {

                    is Resource.Success -> {

                        listOfJob = jobs.data!!

                        if (listOfJob.isNotEmpty()) {
                            isLoading = false
                            isSuccess = true
                        }

                    }

                    is Resource.Error -> {

                        isLoading = false
                        isSuccess = false

                        Log.d("API", "GET-JOBS RESOURCE-ERROR: ${jobs.message}")
                    }

                    else -> {
                        isLoading = false
                    }
                }
            } catch (exception: Exception) {

                isLoading = false

                Log.d("API", "GET-JOBS EXCEPTION: ${exception.message}")
            }
        }
    }
}