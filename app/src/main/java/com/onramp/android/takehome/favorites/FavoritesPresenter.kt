package com.onramp.android.takehome.favorites

import android.content.Context
import android.util.Log
import com.onramp.android.takehome.DependencyInjector
import com.onramp.android.takehome.ImageRepository
import com.onramp.android.takehome.imageData.*
import com.onramp.android.takehome.imageData.source.local.FavoriteImage


class FavoritesPresenter(
        view: FavoritesContract.View,
        dependencyInjector: DependencyInjector) : FavoritesContract.Presenter {

    private val imageRepository: ImageRepository = dependencyInjector.imageRepository()
    private var view: FavoritesContract.View? = view

    override fun onViewCreated() {}

    override suspend fun getFavoriteImageData(activityContext: Context) {
        val imageList = loadFavoriteImages(activityContext)

        if (imageList.isEmpty()) {
            view?.setBlank()
        } else {
            // convert FavoriteImage to Image
            var newImageList = ArrayList<Image>()

            for (imageItem in imageList) {
                val imageUrl = imageItem.url
                val image = Image(
                        "descrp",
                        "today",
                        "descri",
                        "1", Links("","","", ""),
                        Urls("", "", imageUrl.toString()),
                        User("", LinksX(""), "", "", ""))
                newImageList.add(image)
            }
            view?.setImagesOnMainThread(activityContext, newImageList)
        }
    }

    override fun onDestroy() {
        this.view = null
    }

    private suspend fun loadFavoriteImages(context: Context): List<FavoriteImage> {
        return imageRepository.loadFavoriteImages(context)
    }

}