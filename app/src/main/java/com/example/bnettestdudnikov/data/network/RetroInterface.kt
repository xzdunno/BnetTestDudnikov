package com.example.bnettestdudnikov.data.network

import androidx.lifecycle.LiveData
import com.example.bnettestdudnikov.data.model.Drug
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface RetroInterface {
    @GET("api/ppp/index/")
    suspend fun getData(@Query("id") id: String): Response<List<Drug>>

    @GET("api/ppp/index/")
    suspend fun getSearch(@Query("search") search: String): Response<List<Drug>>

    @GET("api/ppp/item/")
    suspend fun getItem(@Query("id") id: String): Response<Drug>
}