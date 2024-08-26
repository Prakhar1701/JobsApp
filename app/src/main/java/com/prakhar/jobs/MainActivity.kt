package com.prakhar.jobs

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.room.Room
import com.prakhar.jobs.data.JobsDatabase
import com.prakhar.jobs.model.JobBookmark
import com.prakhar.jobs.navigation.JobsNavigation
import com.prakhar.jobs.ui.theme.JobsTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @SuppressLint("CoroutineCreationDuringComposition")
    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JobsTheme {
                JobsApp()

                // Building the database
                val db = Room.databaseBuilder(
                    applicationContext,
                    JobsDatabase::class.java, "jobs-database"  // Updated to match your database name
                ).build()

                val dao = db.jobsDao()

                // Insert a job into the database in a background thread
                GlobalScope.launch {
                    val job = JobBookmark(
                        id = 0, // Assuming 0 will be replaced by the auto-generated value in the database.
                        title = "Software Engineer",
                        phone = "123-456-7890",
                        place = "New York, NY",
                        salary = "$100,000"
                    )


                    dao.addJob(job)
                }
            }
        }
    }

}

@Composable
fun JobsApp() {

    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            JobsNavigation()
        }
    }
}