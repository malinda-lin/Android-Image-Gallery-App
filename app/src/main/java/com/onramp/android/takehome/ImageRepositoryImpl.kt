package com.onramp.android.takehome

import com.onramp.android.takehome.imageData.Image
import com.onramp.android.takehome.imageData.source.remote.ImageRemoteDataSource
import retrofit2.Retrofit
import retrofit2.awaitResponse
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.Exception

const val UNSPLASH_BASE_URL = "https://api.unsplash.com"

class ImageRepositoryImpl: ImageRepository {

    override suspend fun loadRandomImages(): List<Image> {
        var imageList = ArrayList<Image>()
        val api = getAPI()
        try {
            val res = api.getRandomImages().awaitResponse()
            if (res.isSuccessful) {
                val data = res.body()!!
                for(item in data) {
                    imageList.add(item)
                }
            }
        } catch (e: Exception) {
            throw Exception("Error in loadRandomImage: $e")
        }
        return imageList
    }

    private fun getAPI(): ImageRemoteDataSource {
        return Retrofit.Builder()
                .baseUrl(UNSPLASH_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create<ImageRemoteDataSource>(ImageRemoteDataSource::class.java)
    }
}