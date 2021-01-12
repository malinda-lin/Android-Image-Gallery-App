package com.onramp.android.takehome.favorites

import android.content.Context
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.GridView
import androidx.core.content.ContextCompat
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.Snackbar
import com.onramp.android.takehome.DependencyInjectorImpl
import com.onramp.android.takehome.adapters.ImageAdapter
import com.onramp.android.takehome.R
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
            val gridView = findViewById<GridView>(R.id.imageGrid)
            val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigation)
            val message = "No Images Found, Try Adding Some!"
            val snackBar = Snackbar.make(gridView, message, Snackbar.LENGTH_LONG)
            val snackView = snackBar.view
            snackView.textAlignment = View.TEXT_ALIGNMENT_CENTER
            snackBar.setAnchorView(bottomNavigationView)
                    .setBackgroundTint(ContextCompat.getColor(applicationContext, R.color.colorPrimary))
                    .setTextColor(Color.WHITE)
                    .show()
        }
    }

    override suspend fun setImagesOnMainThread(activityContext: Context, imageList: ArrayList<Image>) {
        withContext(Dispatchers.Main) {
            setImagesToGridView(activityContext, imageList)
        }
    }

    // bind adapter to gridView
    private fun setImagesToGridView(activityContext: Context, imageList: ArrayList<Image>) {

        adapter = ImageAdapter(activityContext)
        adapter?.setData(imageList)
        val gridView = findViewById<GridView>(R.id.imageGrid)
        gridView.adapter = adapter
    }
}