package com.prakhar.jobs.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "job_bookmarks")
data class JobBookmark(
    @ColumnInfo(name = "job_id") @PrimaryKey(autoGenerate = false)
    val id: Int,
    val title: String,
    val whatsapp_no: String,
    val place: String,
    val salary: String,
    val other_details: String
)
