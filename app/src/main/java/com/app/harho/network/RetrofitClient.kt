package com.app.harho.network

import com.app.harho.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitClient {
    private val retrofitClient = Retrofit.Builder().baseUrl(BuildConfig.BASE_URL).client(client)
        .addConverterFactory(GsonConverterFactory.create()).build()
    companion object{
        private val loggingInterceptora = HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)


        val client = OkHttpClient.Builder()
            .callTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptora)
            .addInterceptor(ClientInterceptor())
            .build()
    }
    fun getApi(): ApiInterface {
        return retrofitClient.create(ApiInterface::class.java)
    }
}