package com.sample.compose.data.localdb

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sample.compose.data.localdb.entity.BreedEntity

@Database(
    entities = [BreedEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
     abstract val breedDao: BreedDao
}
