package com.sample.compose.data.localdb

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.sample.compose.data.localdb.entity.BreedEntity

@Dao
interface BreedDao {

    @Query("select * from breedentity")
    fun getAllBreeds(): PagingSource<Int, BreedEntity>

    @Query("select * from breedentity order by id desc limit 1")
    suspend fun getLastBreed(): BreedEntity?

    @Query("Delete from breedentity")
    suspend fun clearAll()

    @Upsert
    suspend fun upsertData(data:List<BreedEntity>)

    @Query("SELECT COUNT(*) FROM breedentity")
    suspend fun getCount(): Int
}