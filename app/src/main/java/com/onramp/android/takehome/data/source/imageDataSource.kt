package com.onramp.android.takehome.data.source

import com.onramp.android.takehome.data.ImageViewModel
import retrofit2.Call

interface imageDataSource {
    interface LoadFavoriteImagesCallback {
        fun onFavoritesLoaded(images: List<ImageViewModel>)
    }
    interface LoadRandomImagesCallback {
        fun onRandomImagesLoaded(images: List<ImageViewModel>)
    }
    interface LoadSearchImagesCallback {
        fun onSearchImagesLoaded(images: List<ImageViewModel>)
    }
    fun getFavoriteImages(callback: LoadFavoriteImagesCallback)
    fun getRandomImages(callback: LoadRandomImagesCallback): Call<ImageViewModel>
    fun getSearchImages(searchName: String, callback: LoadSearchImagesCallback)

    fun addFavorite(imageId: String)

}