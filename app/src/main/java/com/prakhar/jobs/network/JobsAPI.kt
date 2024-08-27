package com.prakhar.jobs.network

import com.prakhar.jobs.model.Jobs
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface JobsAPI {

    companion object {
        const val BASE_URL = "https://testapi.getlokalapp.com/"
    }

    @GET("common/jobs")
    suspend fun getJobs(@Query("page") page: Int): Jobs
}