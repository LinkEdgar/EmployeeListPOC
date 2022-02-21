package com.example.employeedirectory.networking

import retrofit2.Retrofit

interface NetworkClient {
    fun getClient() : Retrofit
}