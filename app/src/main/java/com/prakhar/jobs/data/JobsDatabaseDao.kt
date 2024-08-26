package com.prakhar.jobs.data

import androidx.room.Dao
import androidx.room.Delete
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


    @Delete
    suspend fun removeJob(job: JobBookmark)


    @Query("SELECT COUNT(*) FROM job_bookmarks WHERE job_id = :jobId LIMIT 1")
    suspend fun isJobExists(jobId: Int): Int    // if return 1 then true, else false
}