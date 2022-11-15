package com.example.bionetsample.network

import com.example.bionetsample.data.Group
import com.example.bionetsample.data.Regions
import com.example.bionetsample.data.Schools
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

private val BASE_URL = "http://bionet.uz/api/"

val loggingInterceptor = HttpLoggingInterceptor().also {
    it.level = HttpLoggingInterceptor.Level.BODY
}

val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL)
    .client(OkHttpClient.Builder().addInterceptor(loggingInterceptor).build())
    .build()

interface BionetApiService {
    @GET("info/regions")
    suspend fun getRegions(): Regions

    @GET("info/schools/{regionId}/{schoolTypeItem}")
    suspend fun getSchools(
        @Path("regionId") regionId: Int,
        @Path("schoolTypeItem") schoolTypeItem: Int
    ): Schools

    @GET("student/getgroups/{regionId}/{schoolTypeItem}")
    suspend fun getGroups(
        @Path("regionId") regionId: Int,
        @Path("SchoolTypeItem") schoolTypeItem: Int
    ): List<Group>
}

object BionetApi {
    val retrofitService: BionetApiService by lazy {
        retrofit.create(BionetApiService::class.java)
    }
}