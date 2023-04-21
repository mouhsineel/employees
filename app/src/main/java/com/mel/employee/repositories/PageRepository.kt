package com.mel.employee.repositories

import com.mel.employee.data.Page
import retrofit2.Response

interface PageRepository {

    suspend fun getUsers(page:Int):Response<Page>
}