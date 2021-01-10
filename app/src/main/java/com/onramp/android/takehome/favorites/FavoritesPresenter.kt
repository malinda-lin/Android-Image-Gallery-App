package com.onramp.android.takehome.favorites

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.onramp.android.takehome.DependencyInjector
import com.onramp.android.takehome.ImageRepository
import com.onramp.android.takehome.imageData.*
import com.onramp.android.takehome.imageData.source.local.FavoriteImage
import java.lang.reflect.Array

class FavoritesPresenter(
        view: FavoritesContract.View,
        dependencyInjector: DependencyInjector) : FavoritesContract.Presenter {

    private val imageRepository: ImageRepository = dependencyInjector.imageRepository()
    private var view: FavoritesContract.View? = view

    override fun onViewCreated() {}

    override suspend fun getFavoriteImageData(activityContext: Context) {
        val imageList = loadFavoriteImages(activityContext)
        Log.d("mylog", "in favoritePresenter")
        if (imageList.isEmpty()) {
            view?.setBlank()
            /*
            // Test for gridView & adapter
            val fakeList = ArrayList<Image>()
            fakeList.add(Image(
                            "descrp",
                            "today",
                            "descri",
                            "1", Links("","","", ""),
                            Urls("", "", ""),
                            User("", LinksX(""), "", "", "")))

            view?.setImagesOnMainThread(activityContext, fakeList as ArrayList<Image>)
             */
        } else {
            Log.d("mylog", "imageList is not empty")
            view?.setImagesOnMainThread(activityContext, imageList as ArrayList<Image>)
        }
    }

    override fun onDestroy() {
        this.view = null
    }

    private suspend fun loadFavoriteImages(context: Context): List<FavoriteImage> {
        return imageRepository.loadFavoriteImages(context)
    }

}