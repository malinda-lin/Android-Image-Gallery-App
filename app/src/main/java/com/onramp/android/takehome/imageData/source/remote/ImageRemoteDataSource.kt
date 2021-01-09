package com.onramp.android.takehome.imageData.source.remote

import com.onramp.android.takehome.BuildConfig
import com.onramp.android.takehome.imageData.Image
import retrofit2.Call
import retrofit2.http.GET

const val API_KEY = BuildConfig.UNSPLASH_API_KEY

interface ImageRemoteDataSource {
    @GET("/photos/random?count=30&client_id=${API_KEY}")
    fun getRandomImages(): Call<List<Image>>
    // TODO: Figure out how to send multiple GET request with Retrofit
//    fun getSearchImages()

}