package com.prakhar.jobs.repository

import com.prakhar.jobs.data.JobsDatabase
import com.prakhar.jobs.data.Resource
import com.prakhar.jobs.model.JobBookmark
import com.prakhar.jobs.model.Result
import com.prakhar.jobs.network.JobsAPI
import javax.inject.Inject

class JobsRepository @Inject constructor(private val api: JobsAPI, private val database: JobsDatabase) {

    suspend fun getJobs(): Resource<List<Result>> {

        return try {

            Resource.Loading(data = true)

            val resultList = api.getJobs().results

            if (resultList.isNotEmpty()) Resource.Loading(data = false)

            Resource.Success(data = resultList)

        } catch (e: Exception) {

            Resource.Error(message = e.message.toString())
        }
    }


   suspend fun bookmarkJob(job : JobBookmark){
        database.jobsDao().addJob(job)
    }

    suspend fun getBookMarkedJobs(): List<JobBookmark> {
        return database.jobsDao().getJobs()
    }
}