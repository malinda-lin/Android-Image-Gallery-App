package com.onramp.android.takehome.favorites

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.GridView
import android.widget.Toast
import com.onramp.android.takehome.DependencyInjectorImpl
import com.onramp.android.takehome.ImageAdapter
import com.onramp.android.takehome.R
import com.onramp.android.takehome.explore.ExploreContract
import com.onramp.android.takehome.explore.ExplorePresenter
import com.onramp.android.takehome.imageData.Image
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FavoritesActivity : AppCompatActivity(), FavoritesContract.View {

    private var adapter: ImageAdapter? = null
    private lateinit var presenter: FavoritesContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorites)

        setPresenter(FavoritesPresenter(this, DependencyInjectorImpl()))

        // async function to retrieve data
        CoroutineScope(Dispatchers.IO).launch { presenter.getFavoriteImageData(this@FavoritesActivity) }
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }

    override fun setPresenter(presenter: FavoritesContract.Presenter) {
        this.presenter = presenter
    }

    override suspend fun setBlank() {
        withContext(Dispatchers.Main) {
            Toast.makeText(applicationContext, "No Images Found, Try Adding Some!", Toast.LENGTH_LONG).show()
        }
    }

    override suspend fun setImagesOnMainThread(activityContext: Context, imageList: ArrayList<Image>) {
        withContext(Dispatchers.Main) {
            setImagesToGridView(activityContext, imageList)
        }
    }

    // bind adapter to gridView
    private fun setImagesToGridView(activityContext: Context, imageList: ArrayList<Image>) {
//        adapter = ImageAdapter(activityContext, imageList)
        adapter = ImageAdapter(activityContext)
        // adapter.setData(
        val gridView = findViewById<GridView>(R.id.imageGrid)
        gridView.adapter = adapter
    }
}