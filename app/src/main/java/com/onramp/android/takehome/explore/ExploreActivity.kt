package com.onramp.android.takehome.explore

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.appbar.MaterialToolbar
import com.onramp.android.takehome.ImageAdapter
import com.onramp.android.takehome.R
import com.onramp.android.takehome.data.ImageViewModel
import com.onramp.android.takehome.data.source.remote.ImageRemoteDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.awaitResponse
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception
import kotlin.collections.ArrayList

const val BASE_URL = "https://api.unsplash.com"

class ExploreActivity : AppCompatActivity() {

    var adapter: ImageAdapter? = null
    var imageList = ArrayList<ImageViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_explore)

        // load topAppBar
        val topAppBar = findViewById<MaterialToolbar>(R.id.topAppBar)
        setSupportActionBar(topAppBar)

        // function to retrieve data
        getCurrentData(this)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    // onOptionsItemSelected is trigger when appbar menu item is clicked
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // item is the menu item clicked
        return when (item.itemId) {
            R.id.action_settings -> true
            R.id.action_about -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    // function that retrieves image data from API
    private fun getCurrentData(thisContext: Context) {
        val api = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create<ImageRemoteDataSource>(ImageRemoteDataSource::class.java)

        // puts data request on data fetching thread
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val res = api.getRandomImages().awaitResponse()
                // add all imageViewModels to imageList
                if (res.isSuccessful) {
                    val data = res.body()!!
                    for(item in data) {
                        imageList.add(item)
                    }
                    // async bind adapter to gridView
                    withContext(Dispatchers.Main) {
                        adapter = ImageAdapter(thisContext, imageList)
                        var gridView = findViewById<GridView>(R.id.imageGrid)
                        gridView.adapter = adapter
                    }
                }
            } catch (e: Exception) {
                Log.d("logs", "Error Fetching Data: $e")
                withContext(Dispatchers.Main) {
                    Toast.makeText(applicationContext,"Problem with connection", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    var focusedView: View? = null
    fun showPhotoDetail(view: View) {
        val imageView = view.findViewById<ImageView>(R.id.cardImageView)
        val textView = view.findViewById<TextView>(R.id.cardTextView)
        val imageButton = view.findViewById<ImageButton>(R.id.favoriteImageButton)

        when(focusedView) {
            view -> {
                textView.visibility = View.INVISIBLE
                imageButton.visibility = View.INVISIBLE
                imageView.alpha = 1f
                focusedView = null
            }
            null -> {
                textView.visibility = View.VISIBLE
                imageButton.visibility = View.VISIBLE
                imageView.alpha = 0.5f
                focusedView = view
            }
            else -> {
                // deselect previous card
                val prevImageView = focusedView!!.findViewById<ImageView>(R.id.cardImageView)
                val prevTextView = focusedView!!.findViewById<TextView>(R.id.cardTextView)
                val prevImageButton = focusedView!!.findViewById<ImageButton>(R.id.favoriteImageButton)
                prevTextView.visibility = View.INVISIBLE
                prevImageButton.visibility = View.INVISIBLE
                prevImageView.alpha = 1f

                // select the current card
                textView.visibility = View.VISIBLE
                imageButton.visibility = View.VISIBLE
                imageView.alpha = 0.5f
                focusedView = view
            }
        }
//        if (focusedView === view) {
//            // hide details
//                textView.visibility = View.INVISIBLE
//                imageButton.visibility = View.INVISIBLE
//                imageView.alpha = 1f
//                focusedView = null
//        } else if (focusedView !== null){
//            // show details
//                textView.visibility = View.VISIBLE
//                imageButton.visibility = View.VISIBLE
//                imageView.alpha = 0.5f
//                focusedView = view
//        }

    }

    fun toggleFavorite(view: View) {

    }
}