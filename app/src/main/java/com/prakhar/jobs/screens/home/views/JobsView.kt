package com.prakhar.jobs.screens.home.views

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.prakhar.jobs.navigation.Detail
import com.prakhar.jobs.screens.home.HomeScreenViewModel

@Composable
fun JobsView(
    navController: NavController,
    viewModel: HomeScreenViewModel
) {
    Surface(color = MaterialTheme.colorScheme.background) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Jobs",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.bodyMedium
            )
            JobsList(navController = navController, viewModel = viewModel)
        }
    }
}

@Composable
fun JobsList(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: HomeScreenViewModel
) {
    val response = viewModel.jobResponse.collectAsLazyPagingItems()

    when (response.loadState.refresh) {
        is LoadState.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
        is LoadState.Error -> {
            val error = (response.loadState.refresh as LoadState.Error).error
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = error.localizedMessage ?: "Failed to load data. Please check your internet connection.",
                    color = MaterialTheme.colorScheme.error,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
        else -> {
            LazyVerticalStaggeredGrid(
                columns = StaggeredGridCells.Fixed(1),
                modifier = modifier.fillMaxSize()
            ) {
                items(response.itemCount) { index ->
                    val job = response[index]
                    job?.let {
                        Card(
                            onClick = {
                                navController.navigate(
                                    Detail(
                                        jobId = job.id,
                                        jobTitle = job.title.takeIf { it.isNotEmpty() } ?: "No Data Available",
                                        jobWhatsappNumber = job.whatsapp_no.takeIf { it.isNotEmpty() } ?: "No Data Available",
                                        jobPlace = job.primary_details.Place.takeIf { it.isNotEmpty() } ?: "No Data Available",
                                        jobSalary = job.primary_details.Salary.takeIf { it.isNotEmpty() } ?: "No Data Available",
                                        jobOtherDetails = job.other_details.takeIf { it.isNotEmpty() } ?: "No Data Available"
                                    )
                                )
                            },
                            modifier = Modifier
                                .padding(16.dp)
                                .fillMaxWidth(),
                            shape = RoundedCornerShape(16.dp)
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
                                    text = "Location: ${job.primary_details?.Place ?: "-"}",
                                    color = MaterialTheme.colorScheme.onSurface,
                                    maxLines = 2,
                                    overflow = TextOverflow.Ellipsis,
                                    style = MaterialTheme.typography.titleSmall
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(
                                    text = "Salary: ${job.primary_details?.Salary ?: "-"}",
                                    color = MaterialTheme.colorScheme.onSurface,
                                    maxLines = 2,
                                    overflow = TextOverflow.Ellipsis,
                                    style = MaterialTheme.typography.titleSmall
                                )
                                Spacer(modifier = Modifier.height(30.dp))
                            }
                        }
                    }
                }
                response.apply {
                    when (loadState.append) {
                        is LoadState.Loading -> {
                            item {
                                Box(
                                    modifier = Modifier.fillMaxWidth(),
                                    contentAlignment = Alignment.Center
                                ) {
                                    CircularProgressIndicator()
                                }
                            }
                        }
                        is LoadState.Error -> {
                            item {
                                Text(
                                    text = "Error loading more jobs.",
                                    color = MaterialTheme.colorScheme.error,
                                    style = MaterialTheme.typography.bodyLarge,
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
                        else -> {
                            // Do nothing
                        }
                    }
                }
            }
        }
    }
}
