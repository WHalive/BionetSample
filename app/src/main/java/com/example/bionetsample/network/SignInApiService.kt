package com.example.bionetsample.network

import com.example.bionetsample.data.Regions
import com.example.bionetsample.data.Schools
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

private val BASE_URL = "http://bionet.uz/api/"

val loggingInterceptor = HttpLoggingInterceptor().also {
    it.level = HttpLoggingInterceptor.Level.BODY
}

val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL)
    .client(OkHttpClient.Builder().addInterceptor(loggingInterceptor).build())
    .build()

interface SignInApiService {
    @GET("info/regions")
    suspend fun getRegions(): Regions

    @GET("info/schools")
    suspend fun geSchools(): Schools
}

object SignInApi {
    val retrofitService: SignInApiService by lazy {
        retrofit.create(SignInApiService::class.java)
    }
}