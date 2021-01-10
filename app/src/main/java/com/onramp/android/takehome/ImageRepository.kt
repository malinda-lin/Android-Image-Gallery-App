package com.onramp.android.takehome

import android.content.Context
import com.onramp.android.takehome.imageData.Image
import com.onramp.android.takehome.imageData.source.local.FavoriteImage

interface ImageRepository {
    suspend fun loadRandomImages() : List<Image>
    suspend fun loadFavoriteImages(context: Context) : List<FavoriteImage>
    suspend fun saveFavoriteImage(context: Context ,imageData: FavoriteImage)
}