package com.onramp.android.takehome.repository

import android.content.Context
import com.onramp.android.takehome.imageData.Image
import com.onramp.android.takehome.imageData.source.local.FavoriteImage
import com.onramp.android.takehome.imageData.source.local.FavoriteImageDatabase
import com.onramp.android.takehome.imageData.source.remote.ImageRemoteDataSource
import com.onramp.android.takehome.repository.ImageRepository
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

    override suspend fun loadFavoriteImages(context: Context): List<FavoriteImage> {
        val db = getDB(context)
        val imageDao = db.imageDao()
        var imageList: List<FavoriteImage>

        try {
            imageList = imageDao.getAllImages()
        } catch (e: Exception) {
            throw Exception("Error in loadRandomImage (Room DB): $e")
        }

        return imageList
    }

    override suspend fun saveFavoriteImage(context: Context, imageData: FavoriteImage) {
        val db = getDB(context)
        val imageDao = db.imageDao()

        try {
            imageDao.addImage(imageData)
        } catch (e: Exception) {
            throw Exception("Error in saveFavoriteImage (Room DB): $e")
        }
    }

    private fun getAPI(): ImageRemoteDataSource {
        return Retrofit.Builder()
                .baseUrl(UNSPLASH_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create<ImageRemoteDataSource>(ImageRemoteDataSource::class.java)
    }

    private fun getDB(context: Context): FavoriteImageDatabase {
        return FavoriteImageDatabase.getDatabase(context)
    }
}