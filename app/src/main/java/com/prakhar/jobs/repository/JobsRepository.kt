package com.prakhar.jobs.repository

import com.prakhar.jobs.data.JobsDatabase
import com.prakhar.jobs.data.Resource
import com.prakhar.jobs.model.JobBookmark
import com.prakhar.jobs.model.Result
import com.prakhar.jobs.network.JobsAPI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class JobsRepository @Inject constructor(private val api: JobsAPI, private val database: JobsDatabase) {


    suspend fun getJobs(page : Int): List<Result> {

        val resultList = api.getJobs(page).results

        return resultList

    }


   suspend fun bookmarkJob(job : JobBookmark){
        database.jobsDao().addJob(job)
    }

   suspend fun removeBookmarkJob(job: JobBookmark){
       database.jobsDao().removeJob(job)
   }

    fun getBookmarkedJobs(): Flow<List<JobBookmark>> {
        return database.jobsDao().getJobs().flowOn(Dispatchers.IO).conflate()
    }

    suspend fun isJobBookmarked(jobId: Int): Boolean{
        return database.jobsDao().isJobExists(jobId) == 1
    }
}