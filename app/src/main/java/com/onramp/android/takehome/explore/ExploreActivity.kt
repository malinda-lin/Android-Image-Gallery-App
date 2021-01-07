package com.onramp.android.takehome.explore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.onramp.android.takehome.R

class ExploreActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_explore)

        val topAppBar = findViewById<Toolbar>(R.id.topAppBar)
        setSupportActionBar(topAppBar)

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    // onOptionsItemSelected is trigger when appbar menu item is clicked
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // item is the menu item clicked
        val id = item.itemId
        Log.d("myLog", "Value: "+ item.itemId.toString())
        return when (id) {
            R.id.action_settings -> true
            R.id.action_about -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}