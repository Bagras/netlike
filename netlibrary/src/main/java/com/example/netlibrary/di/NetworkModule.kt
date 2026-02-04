package com.example.netlibrary.di

import com.example.netlibrary.api.ApiService
import com.example.netlibrary.datasource.RemoteDataSource
import com.example.netlibrary.repository.Repository
import com.example.netlibrary.repository.RepositoryImpl
import com.example.netlibrary.retrofit.RetrofitClient

object NetworkModule {
    private const val BASE_URL = "http://192.168.21.44:443/api/"

    private val apiService: ApiService by lazy {
        RetrofitClient.create(BASE_URL)
    }

    private val remoteDataSource: RemoteDataSource by lazy {
        RemoteDataSource(apiService)
    }

    val repository: Repository by lazy {
        RepositoryImpl(remoteDataSource)
    }
}
