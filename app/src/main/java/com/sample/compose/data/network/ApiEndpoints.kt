package com.sample.compose.data.network

import com.sample.compose.data.dto.BreedDto
import com.sample.compose.data.dto.FactDto
import com.sample.compose.data.dto.GroupDto
import com.sample.compose.data.dto.ListResponse
import com.sample.compose.data.dto.PaginatedResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiEndpoints {
    @GET("breeds")
    suspend fun getAllBreeds(@Query("page[number]") pageNumber: Int?,
                             @Query("page[size]") pageSize: Int?): ApiResponse<PaginatedResponse<BreedDto>>

    @GET("facts")
    suspend fun getFacts(@Query("limit") limit: Int?): ApiResponse<ListResponse<FactDto>>


    @GET("groups")
    suspend fun getGroups(@Query("page[number]") pageNumber: Int?,
                             @Query("page[size]") pageSize: Int?): ApiResponse<PaginatedResponse<GroupDto>>
}