package com.sample.compose.data.dto

data class PaginatedResponse<T>(val data: List<ResponseType<T>>, val meta: ResponseMetaData, val links: Links)

data class ListResponse<T>(val data: List<ResponseType<T>>)
data class ResponseType<T>(val id: String, val type: String, val attributes: T)
data class ResponseMetaData(val pagination: PaginationData)


data class PaginationData(val current: Int, val next: Int, val last: Int, val records: Int)


data class Links(val self: String, val current: String, val next: String, val last: String)