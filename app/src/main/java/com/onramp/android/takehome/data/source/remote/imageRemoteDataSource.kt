package com.onramp.android.takehome.data.source.remote

import com.onramp.android.takehome.data.ImageViewModel
import com.onramp.android.takehome.data.source.imageDataSource
import retrofit2.Call
import retrofit2.http.GET

interface imageRemoteDataSource {
    @GET("/photos/random?count=30&client_id=")
    fun getRandomImages(callback: imageDataSource.LoadRandomImagesCallback): Call<ImageViewModel>
    fun getSearchImages(searchName: String, callback: imageDataSource.LoadSearchImagesCallback)

}