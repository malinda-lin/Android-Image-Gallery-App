package com.onramp.android.takehome.explore

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.GridView
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.android.material.appbar.MaterialToolbar
import com.onramp.android.takehome.ImageAdapter
import com.onramp.android.takehome.R
import com.onramp.android.takehome.data.ImageViewModel

const val BASE_URL = "https://api.unsplash.com/"

class ExploreActivity : AppCompatActivity() {

    var adapter: ImageAdapter? = null
    var imageList = ArrayList<ImageViewModel>()

    // DELETE UPON SUBMISSION
    private var TAG = "ExploreActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_explore)

        // load topAppBar
        val topAppBar = findViewById<MaterialToolbar>(R.id.topAppBar)
        setSupportActionBar(topAppBar)

        // added placeholder data to imageList
        // TODO: create method to fetch and populate imageList
        imageList.add(ImageViewModel(
                "image1",
                "testImage",
                "test description",
                "https://images.unsplash.com/photo-1609910276253-4ed82b146992?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=934&q=80",
                "Malinda",
                1,
                0,
                "download link",
                "location")
        )

        // bind adapter to gridView
        adapter = ImageAdapter(this, imageList)
        var gridView = findViewById<GridView>(R.id.imageGrid)
        gridView.adapter = adapter
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

    // function for debugging, DELETE UPON SUBMISSION
    fun loadImage(view: View?) {
        val url = "https://images.unsplash.com/photo-1609910276253-4ed82b146992?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=934&q=80"
        var imageView = findViewById<ImageView>(R.id.imageView)
        Glide.with(this)
                .load(url)
                .centerCrop()
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(imageView)
    }

}