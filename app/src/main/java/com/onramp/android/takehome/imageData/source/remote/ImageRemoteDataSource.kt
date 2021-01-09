package com.onramp.android.takehome.imageData.source.remote

import com.onramp.android.takehome.BuildConfig
import com.onramp.android.takehome.imageData.Image
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

const val API_KEY = BuildConfig.UNSPLASH_API_KEY

// This interface defines all query routes
interface ImageRemoteDataSource {

    @GET("/photos/random?")
    fun getRandomImages(
        @Query("count") count: String = "30",
        @Query("client_id") client_id: String = API_KEY
    ): Call<List<Image>>

    @GET("/search/photos?")
    fun getSearchImages(
            @Query("query") query: String,
            @Query("order_by") order_by: String = "latest",
            @Query("client_id") client_id: String = API_KEY
    ): Call<List<Image>>

}