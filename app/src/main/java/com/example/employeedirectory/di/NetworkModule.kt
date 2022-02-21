package com.example.employeedirectory.di

import com.example.employeedirectory.networking.EmployeeService
import com.example.employeedirectory.networking.RetrofitClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    fun providesEmployeeService(client : RetrofitClient) : EmployeeService {
        return client.getClient().create(EmployeeService::class.java)
    }

}