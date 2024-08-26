package com.prakhar.jobs.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.prakhar.jobs.model.JobBookmark
import com.prakhar.jobs.model.Result

@Dao
interface JobsDatabaseDao{

    @Query(value = "SELECT * FROM job_bookmarks")
    suspend fun getJobs(): List<JobBookmark>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addJob(job : JobBookmark)
}