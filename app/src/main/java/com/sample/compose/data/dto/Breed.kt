package com.sample.compose.data.dto

import com.google.gson.annotations.SerializedName
import com.sample.compose.data.localdb.entity.BreedEntity

data class BreedDto(
    val name: String,
    val description: String,
    val life: LifeDto,
    @SerializedName("male_weight") val maleWeight: WeightDto,
    @SerializedName("female_weight") val femaleWeight: WeightDto,
    val hypoallergenic: Boolean,
)

fun BreedDto.toBreedEntity() = BreedEntity(
    breedName = name,
    description = description,
    maxLife = life.max,
    minLife = life.min,
    hypoallergic = hypoallergenic,
    maxMaleWeight = maleWeight.max,
    minMaleWeight = maleWeight.min,
    maxFemaleWeight = femaleWeight.max,
    minFemaleWeight = femaleWeight.min
)

data class LifeDto(val max: Int, val min: Int)

data class WeightDto(val max: Int, val min: Int)