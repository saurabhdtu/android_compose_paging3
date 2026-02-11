package com.sample.compose.data.repository

import com.sample.compose.data.dto.FactDto
import com.sample.compose.data.dto.ListResponse
import com.sample.compose.data.network.ApiEndpoints
import com.sample.compose.data.network.ApiResponse
import javax.inject.Inject
import javax.inject.Singleton

interface FactRepository {
    suspend fun getFacts(limit: Int? = null): ApiResponse<ListResponse<FactDto>>
}

@Singleton
class FactRepositoryImpl @Inject constructor(
    private val apiEndpoints: ApiEndpoints
) : FactRepository {
    override suspend fun getFacts(limit: Int?): ApiResponse<ListResponse<FactDto>> {
        return apiEndpoints.getFacts(limit)
    }
}
