package com.sample.compose.data.localdb.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.sample.compose.ui.screen.dogs.BreedUiItem

@Entity
data class BreedEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val breedName:String,
    val description:String,
    val maxLife:Int,
    val minLife:Int,
    val hypoallergic: Boolean,
    val maxMaleWeight:Int,
    val minMaleWeight:Int,
    val maxFemaleWeight:Int,
    val minFemaleWeight:Int,
    val time: Long = System.currentTimeMillis()
    )


fun BreedEntity.toBreedUiItem() = BreedUiItem(
    breedName = breedName,
    description = description,
    maxLife = maxLife,
    minLife = minLife,
    hypoallergic = hypoallergic
)