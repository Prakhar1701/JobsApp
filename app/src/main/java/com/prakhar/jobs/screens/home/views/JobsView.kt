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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.prakhar.jobs.model.Result
import com.prakhar.jobs.screens.home.HomeScreenViewModel

@Composable
fun JobsView(viewModel: HomeScreenViewModel){


    Surface(
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = "Jobs",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(modifier = Modifier.height(16.dp))

            JobColumn(viewModel = viewModel)
        }
    }
}

@Composable
private fun JobColumn(
    viewModel: HomeScreenViewModel
) {
    val listOfJob = viewModel.listOfJob

    if (viewModel.isLoading) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(
                color = MaterialTheme.colorScheme.primary
            )
        }
    } else if (!viewModel.isSuccess) {

        Text(
            text = "Something went wrong, unable to load jobs\u2757",
            color = MaterialTheme.colorScheme.error,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.fillMaxWidth(),
            // textAlign = Alignment.CenterHorizontally
        )

    } else {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(listOfJob) { job ->
                if (job.type == 1009) {
                    JobCard(job = job)
                }
            }
        }
    }
}

@Composable
fun JobCard(job: Result, onClick: () -> Unit = {}) {
    Card(
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(8.dp)
    ) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.surface
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = "Title:\n" + job.title,
                    color = MaterialTheme.colorScheme.onSurface,
                    maxLines = 10,
                    overflow = TextOverflow.Ellipsis,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.titleMedium
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Phone: ${job.whatsapp_no}",
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 10,
                    overflow = TextOverflow.Ellipsis,
                    fontWeight = FontWeight.Normal,
                    style = MaterialTheme.typography.titleSmall
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = job.primary_details.Place,
                    color = MaterialTheme.colorScheme.onSurface,
                    maxLines = 10,
                    overflow = TextOverflow.Ellipsis,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.titleMedium
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = job.primary_details.Salary,
                    color = MaterialTheme.colorScheme.onSurface,
                    maxLines = 10,
                    overflow = TextOverflow.Ellipsis,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }
    }
}

