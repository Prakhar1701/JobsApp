package com.prakhar.jobs.screens.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.prakhar.jobs.model.JobBookmark
import com.prakhar.jobs.navigation.Detail

@Composable
fun DetailScreen(jobDetail: Detail,
                 viewModel: DetailScreenViewModel = hiltViewModel()) {

    viewModel.checkJobBookmark(jobDetail.jobId)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(20.dp)
            .verticalScroll(rememberScrollState())
    ){

        Text(
            text = "Other Job Details:\n",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground,
            style = MaterialTheme.typography.bodyMedium
        )


        Text(
            text = jobDetail.jobOtherDetails ,
            color = MaterialTheme.colorScheme.onSurface,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(modifier = Modifier.height(100.dp))



Column ( verticalArrangement = Arrangement.SpaceBetween,
    horizontalAlignment = Alignment.CenterHorizontally,
    modifier = Modifier.fillMaxSize()){


    FloatingActionButton(
        contentColor = Color.Red,
        containerColor = MaterialTheme.colorScheme.background,
        shape = RoundedCornerShape(16.dp), // Rounded corners
        elevation = FloatingActionButtonDefaults.elevation(),
        modifier = Modifier
            .height(120.dp)
            .width(120.dp),
        onClick = {


            if(viewModel.isBookmarked){

                viewModel.removeJobBookmark(
                    jobDetail.toJobBookmark()
                )

                viewModel.isBookmarked = false

            }else{

                viewModel.addJobBookmark(
                    jobDetail.toJobBookmark()
                )
                viewModel.isBookmarked = true
            }

        }
    ) {
        if (viewModel.isBookmarked){
            Icon(
                imageVector = Icons.Filled.Favorite,
                contentDescription = "Bookmark Button",
                modifier = Modifier.size(60.dp)
            )
        }else{
            Icon(
                imageVector = Icons.Outlined.FavoriteBorder,
                contentDescription = "Bookmark Button",
                modifier = Modifier.size(60.dp)
            )
        }

    }

Spacer(modifier = Modifier.height(20.dp))

    Text(text = "Bookmark")
} }
    }



// Extension function to map Detail to JobBookmark
fun Detail.toJobBookmark(): JobBookmark {
    return JobBookmark(
        id = this.jobId, // Assuming you want to set this value directly
        title = this.jobTitle,
        whatsapp_no = this.jobWhatsappNumber,
        place = this.jobPlace,
        salary = this.jobSalary,
        other_details = this.jobOtherDetails
    )
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
