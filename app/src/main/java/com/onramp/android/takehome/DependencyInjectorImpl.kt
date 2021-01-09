package com.onramp.android.takehome

class DependencyInjectorImpl : DependencyInjector {
    override fun imageRepository(): ImageRepository {
        return ImageRepositoryImpl()
    }
}