package com.sample.compose.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.sample.compose.data.dto.BreedDto
import com.sample.compose.data.dto.PaginatedResponse
import com.sample.compose.data.localdb.AppDatabase
import com.sample.compose.data.localdb.entity.BreedEntity
import com.sample.compose.data.network.ApiEndpoints
import com.sample.compose.data.network.ApiResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

interface BreedRepository {
    suspend fun getAllBreeds(
        pageNumber: Int? = null,
        pageSize: Int? = null
    ): ApiResponse<PaginatedResponse<BreedDto>>

    fun getPagingData(): Flow<PagingData<BreedEntity>>
}

@OptIn(ExperimentalPagingApi::class)
@Singleton
class BreedRepositoryImpl @Inject constructor(
    private val apiEndpoints: ApiEndpoints,
    private val database: AppDatabase,
) : BreedRepository {


    private val pager = Pager(
        config = PagingConfig(
            pageSize = 25,
            initialLoadSize = 50,
            prefetchDistance = 5,
            enablePlaceholders = false
        ),
        remoteMediator = BreedRemoteMediator(database, this),
        pagingSourceFactory = {
            database.breedDao.getAllBreeds()
        }
    )

    override suspend fun getAllBreeds(
        pageNumber: Int?,
        pageSize: Int?
    ): ApiResponse<PaginatedResponse<BreedDto>> {
        return apiEndpoints.getAllBreeds(pageNumber, pageSize)
    }

    override fun getPagingData(): Flow<PagingData<BreedEntity>> {
        return pager.flow
    }
}
