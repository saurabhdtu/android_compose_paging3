package com.sample.compose.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.sample.compose.data.dto.BreedDto
import com.sample.compose.data.dto.toBreedEntity
import com.sample.compose.data.localdb.AppDatabase
import com.sample.compose.data.localdb.entity.BreedEntity
import com.sample.compose.data.network.ApiResponse
import okio.IOException

@OptIn(ExperimentalPagingApi::class)
class BreedRemoteMediator(val database: AppDatabase, val repository: BreedRepository) :
    RemoteMediator<Int, BreedEntity>() {
//
//    override suspend fun initialize(): InitializeAction {
//        val breed = database.breedDao.getLastBreed()
//        if (breed == null)
//            return InitializeAction.LAUNCH_INITIAL_REFRESH
//        else if (System.currentTimeMillis() - breed.time > 60000)
//            return InitializeAction.LAUNCH_INITIAL_REFRESH
//        return InitializeAction.SKIP_INITIAL_REFRESH
//    }


    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, BreedEntity>
    ): MediatorResult {
        try {
            val loadKey = when (loadType) {
                LoadType.REFRESH -> 1
                LoadType.PREPEND -> return MediatorResult.Success(
                    endOfPaginationReached = true
                )

                LoadType.APPEND -> {
                    val count = database.breedDao.getCount()
                    if (count == 0) return MediatorResult.Success(endOfPaginationReached = false)
                    (count / state.config.pageSize) + 1
                }
            }


            val result = repository.getAllBreeds(loadKey, state.config.pageSize)
            var isEmpty = false
            if (result is ApiResponse.Success) {
                val list = result.data.data.map { (it.attributes as BreedDto).toBreedEntity() }
                isEmpty = list.isEmpty()
                database.withTransaction {
                    if (loadType == LoadType.REFRESH) database.breedDao.clearAll()
                    database.breedDao.upsertData(list)
                }
            }

            return MediatorResult.Success(endOfPaginationReached = isEmpty)
        } catch (e: IOException) {
            return MediatorResult.Error(e)
        } catch (e: Exception) {
            return MediatorResult.Error(e)
        }
    }
}