package com.prakhar.jobs.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.prakhar.jobs.model.JobBookmark

@Database(
    entities = [JobBookmark::class],
    version = 1,
    exportSchema = false
)
abstract class JobsDatabase : RoomDatabase() {
    abstract fun jobsDao(): JobsDatabaseDao
}