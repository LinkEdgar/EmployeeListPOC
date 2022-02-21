package com.example.employeedirectory.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DispatcherModule {

    @Qualifier
    annotation class IODispatcher

    @IODispatcher
    @Singleton
    @Provides
    fun providesIODispatcher() : CoroutineDispatcher {
        return Dispatchers.IO
    }

}