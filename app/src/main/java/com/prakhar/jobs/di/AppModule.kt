package com.prakhar.jobs.di

import android.content.Context
import androidx.room.Room
import com.prakhar.jobs.data.JobsDatabase
import com.prakhar.jobs.data.JobsDatabaseDao
import com.prakhar.jobs.network.JobsAPI
import com.prakhar.jobs.repository.JobsRepository
import com.prakhar.jobs.utils.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideJobsRepository(api: JobsAPI) = JobsRepository(api)

        @Singleton
    @Provides
    fun provideRecipesAPI(): JobsAPI {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create(JobsAPI::class.java)
    }

    @Singleton
    @Provides
    fun provideJobsDao(jobsDatabase: JobsDatabase):JobsDatabaseDao = jobsDatabase.jobsDao()

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): JobsDatabase =
        Room.databaseBuilder(
            context,
            JobsDatabase::class.java,
            name = "jobs_db"
        ).fallbackToDestructiveMigration()
            .build()
}
