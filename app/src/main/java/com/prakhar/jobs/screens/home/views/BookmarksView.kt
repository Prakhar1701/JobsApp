package com.prakhar.jobs.screens.home.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.prakhar.jobs.model.JobBookmark
import com.prakhar.jobs.navigation.Detail
import com.prakhar.jobs.screens.home.HomeScreenViewModel

@Composable
fun JobBookmark(navController: NavController,
                viewModel: HomeScreenViewModel) {

    Surface(
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Bookmarks",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(modifier = Modifier.height(16.dp))

            JobColumn(navController,viewModel = viewModel)
        }

    }
}


@Composable
private fun JobColumn(
    navController: NavController,
    viewModel: HomeScreenViewModel,
) {

    val jobBookmarkItemList = viewModel.jobItemList.collectAsState().value
 
    Text(text = jobBookmarkItemList.size.toString(),
        fontWeight = FontWeight.Bold)

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(jobBookmarkItemList) { job ->

           
                    JobCard(job = job){
                        navController.navigate(


                            Detail(
                                jobId = job.id,
                                job.title.takeIf { it.isNotEmpty() } ?: "No Data Available",
                                job.whatsapp_no.takeIf { it.isNotEmpty() } ?: "No Data Available",
                                job.place.takeIf { it.isNotEmpty() } ?: "No Data Available",
                                job.salary.takeIf { it.isNotEmpty() } ?: "No Data Available",
                                job.other_details.takeIf { it.isNotEmpty() } ?: "No Data Available")
                        )
                    }

            }
        }
    }




@Composable
fun JobCard(job: JobBookmark, onClick: () -> Unit = {}) {
    Card(onClick = { onClick() },

        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(16.dp), // Rounded corners
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = job.title ?: "-",
                color = MaterialTheme.colorScheme.onSurface,
                maxLines = 7,
                overflow = TextOverflow.Ellipsis,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Phone: ${job.whatsapp_no ?: "-"}",
                color = MaterialTheme.colorScheme.onSurface,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.titleSmall
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Location: ${job.place ?: "-"}",
                color = MaterialTheme.colorScheme.onSurface,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.titleSmall
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Salary: ${job.salary?: "-"}",
                color = MaterialTheme.colorScheme.onSurface,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.titleSmall
            )

            Spacer(modifier = Modifier.height(30.dp))
        }
    }
}


// Extension function to map JobBookmark to Detail
fun JobBookmark.toDetail(): Detail {
    return Detail(
        jobId = this.id,
        jobTitle = this.title,
        jobWhatsappNumber = this.whatsapp_no,
        jobPlace = this.place,
        jobSalary = this.salary,
        jobOtherDetails = this.other_details
    )
}