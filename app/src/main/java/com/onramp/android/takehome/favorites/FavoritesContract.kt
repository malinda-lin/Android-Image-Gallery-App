package com.onramp.android.takehome.favorites

import android.content.Context
import com.onramp.android.takehome.BasePresenter
import com.onramp.android.takehome.BaseView
import com.onramp.android.takehome.imageData.Image

interface FavoritesContract {
    interface Presenter: BasePresenter{
        fun onViewCreated()
        suspend fun getFavoriteImageData(activityContext: Context)
    }
    interface View: BaseView<Presenter>{
        suspend fun setImagesOnMainThread(activityContext: Context, imageList: ArrayList<Image>)
        suspend fun setBlank()
    }
}