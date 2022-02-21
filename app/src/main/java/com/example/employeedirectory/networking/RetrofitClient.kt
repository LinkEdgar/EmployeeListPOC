package com.example.employeedirectory.networking

import com.example.employeedirectory.Constants.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RetrofitClient @Inject constructor(

) : NetworkClient{
    override fun getClient(): Retrofit {
        return Retrofit.Builder().client(okhttpClient()).baseUrl(BASE_URL).addConverterFactory(
            MoshiConverterFactory.create().asLenient()).build()
    }

    private fun okhttpClient(): OkHttpClient {
        //todo only enable in debug
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder()
            .addNetworkInterceptor(logging)
            .build()
    }
}