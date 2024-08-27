package com.prakhar.jobs.data

import android.net.http.HttpException
import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.prakhar.jobs.model.Result
import com.prakhar.jobs.repository.JobsRepository
import java.io.IOException
import javax.inject.Inject

class JobsPagingSource @Inject constructor(
    private val repository: JobsRepository
) : PagingSource<Int, Result>() {

    override fun getRefreshKey(state: PagingState<Int, Result>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Result> {
        val page = params.key ?: 1
        return try {
            val response = repository.getJobs(page)
            LoadResult.Page(
                data = response,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (response.isEmpty()) null else page + 1
            )
        } catch (e: IOException) {
            // Handle network-related exceptions (e.g., no internet connection)
            LoadResult.Error(e)
        } catch (e: HttpException) {
            // Handle HTTP protocol errors (e.g., 404, 500)
            LoadResult.Error(e)
        } catch (e: Exception) {
            // Catch any other exceptions that might cause a crash
            LoadResult.Error(e)
        }
    }
}