package com.prakhar.jobs.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "job_bookmarks")
data class JobBookmark(
    @ColumnInfo(name = "job_id") @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    val phone: String,
    val place: String,
    val salary: String
)
