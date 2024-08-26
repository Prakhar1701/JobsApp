package com.prakhar.jobs.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.prakhar.jobs.model.JobBookmark

@Dao
interface JobsDatabaseDao{

    @Query(value = "SELECT * FROM job_bookmarks")
    suspend fun getJobs(): List<JobBookmark>


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addJob(job : JobBookmark)
}