package com.onramp.android.takehome

import com.onramp.android.takehome.imageData.Image

interface ImageRepository {
    suspend fun loadRandomImages() : List<Image>
}