package com.onramp.android.takehome

import com.onramp.android.takehome.repository.ImageRepository
import com.onramp.android.takehome.repository.ImageRepositoryImpl

class DependencyInjectorImpl : DependencyInjector {
    override fun imageRepository(): ImageRepository {
        return ImageRepositoryImpl()
    }
}