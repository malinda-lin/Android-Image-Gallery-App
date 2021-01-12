package com.onramp.android.takehome

import com.onramp.android.takehome.repository.ImageRepository

interface DependencyInjector {
    fun imageRepository() : ImageRepository
}