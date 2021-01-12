package com.onramp.android.takehome.explore

import android.content.Context
import com.onramp.android.takehome.DependencyInjector
import com.onramp.android.takehome.repository.ImageRepository
import com.onramp.android.takehome.imageData.Image
import com.onramp.android.takehome.imageData.source.local.FavoriteImage

class ExplorePresenter(
        view: ExploreContract.View,
        dependencyInjector: DependencyInjector) : ExploreContract.Presenter {

    private val imageRepository: ImageRepository = dependencyInjector.imageRepository()
    private var view: ExploreContract.View? = view

    override fun onViewCreated() {}

    override suspend fun getRandomImageData(activityContext: Context) {
        val imageList = loadRandomImages()

        if (imageList.isEmpty()) view?.setBlank()
        else view?.setImagesOnMainThread(activityContext, imageList as ArrayList<Image>)

    }

    override suspend fun saveFavoriteImage(context: Context, imageData: FavoriteImage) {
        imageRepository.saveFavoriteImage(context, imageData)
    }

    override fun onDestroy() {
        this.view = null
    }

    private suspend fun loadRandomImages(): List<Image> {
        return imageRepository.loadRandomImages()
    }

}