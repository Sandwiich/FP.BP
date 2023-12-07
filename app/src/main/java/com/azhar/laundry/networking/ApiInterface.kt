package com.azhar.laundry.networking

import com.azhar.laundry.model.response.ModelResultNearby
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("place/nearbysearch/json")
    fun getDataResult(
        @Query("key") key: String?,
        @Query("keyword") keyword: String?,
        @Query("location") location: String?,
        @Query("rankby") rankby: String?
    ): Call<ModelResultNearby?>?
}