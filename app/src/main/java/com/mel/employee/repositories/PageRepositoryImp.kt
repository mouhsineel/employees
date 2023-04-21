package com.mel.employee.repositories

import com.mel.employee.data.Page
import com.mel.employee.network.WebAPI
import retrofit2.Response

class PageRepositoryImp (
    private val api:WebAPI
        ): PageRepository {
    override suspend fun getUsers(page: Int): Response<Page> = api.getUsersPerPage(page)
}