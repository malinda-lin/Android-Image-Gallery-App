package com.onramp.android.takehome.explore

import android.content.Context
import android.widget.Toast
import com.onramp.android.takehome.DependencyInjector
import com.onramp.android.takehome.ImageRepository
import com.onramp.android.takehome.imageData.Image

class ExplorePresenter(
        view: ExploreContract.View,
        dependencyInjector: DependencyInjector) : ExploreContract.Presenter {

    private val imageRepository: ImageRepository = dependencyInjector.imageRepository()
    private var view: ExploreContract.View? = view

    override fun onViewCreated() {}

    override suspend fun getRandomImageData(activityContext: Context) {
        val imageList = loadRandomImages()

        if (imageList.isEmpty()) {
            view?.setBlank()
        } else {
            view?.setImagesOnMainThread(activityContext, imageList as ArrayList<Image>)
        }
    }

    override fun onDestroy() {
        this.view = null
    }

    private suspend fun loadRandomImages(): List<Image> {
        return imageRepository.loadRandomImages()
    }

}