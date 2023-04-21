package com.mel.employee.network

import com.mel.employee.data.Page
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WebAPI {

    @GET("users")
    suspend fun getUsersPerPage(@Query("page") page:Int): Response<Page>
}