package com.sample.compose.data

import android.content.Context
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.room.Room
import com.sample.compose.data.localdb.AppDatabase
import com.sample.compose.data.localdb.BreedDao
import com.sample.compose.data.localdb.entity.BreedEntity
import com.sample.compose.data.network.ApiEndpoints
import com.sample.compose.data.network.ApiResponseAdapterFactory
import com.sample.compose.data.repository.BreedRemoteMediator
import com.sample.compose.data.repository.BreedRepository
import com.sample.compose.utils.constants.Config
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@OptIn(ExperimentalPagingApi::class)
@InstallIn(SingletonComponent::class)
object DataModule{
    @Provides
    @Singleton
    fun getDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideBreedDao(database: AppDatabase): BreedDao {
        return database.breedDao
    }

    @Provides
    @Singleton
    fun provideApiClient(): ApiEndpoints = Retrofit.Builder()
        .baseUrl(Config.baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(ApiResponseAdapterFactory())
        .client(
            OkHttpClient
                .Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build()
        )
        .build()
        .create(ApiEndpoints::class.java)
}
