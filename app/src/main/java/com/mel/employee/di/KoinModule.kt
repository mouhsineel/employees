package com.mel.employee.di

import com.mel.employee.network.NetworkIntercepter
import com.mel.employee.network.WebAPI
import com.mel.employee.repositories.PageRepository
import com.mel.employee.repositories.PageRepositoryImp
import com.mel.employee.ui.viewmodel.EmployeesViewModel
import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module{


    single {
        val okhttp = OkHttpClient.Builder()
        .addInterceptor(NetworkIntercepter(get()))
        .build()
        Retrofit.Builder()
            .client(okhttp)
            .baseUrl("https://reqres.in/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WebAPI::class.java)
    }
    single <PageRepository>{
        PageRepositoryImp(get())
    }
    viewModel{
        EmployeesViewModel(get())
    }

}