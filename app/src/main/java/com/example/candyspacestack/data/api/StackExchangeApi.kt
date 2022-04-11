package com.example.candyspacestack.data.api

import com.example.candyspacestack.data.model.SearchResponseSchema
import retrofit2.http.GET
import retrofit2.http.Query

interface StackExchangeApi {
    @GET("2.3/users")
    suspend fun searchUser(
        @Query("inname") query: String,
        @Query("sort") sort: String,
        @Query("order") order: String,
        @Query("page") page: Int,
        @Query("pagesize") pageSize: Int,
        @Query("site") site: String,
    ): SearchResponseSchema
}
