package com.onramp.android.takehome.explore

import android.content.Context
import com.onramp.android.takehome.BasePresenter
import com.onramp.android.takehome.BaseView
import com.onramp.android.takehome.imageData.Image

interface ExploreContract {

    interface Presenter : BasePresenter {
        fun onViewCreated()
        suspend fun getRandomImageData(activityContext: Context)
    }

    interface View : BaseView<Presenter> {
        suspend fun setImagesOnMainThread(activityContext: Context, imageList: ArrayList<Image>)
    }
}