package com.onramp.android.takehome.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.onramp.android.takehome.R

class BottomNavigationBar : Fragment(R.layout.fragment_bottom_nav_bar) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bottomNavigationView = view.findViewById<BottomNavigationView>(R.id.bottomNavigation)
        bottomNavigationView.setOnNavigationItemSelectedListener { item -> setNavigationButtonListener(item)}
    }

    private fun setNavigationButtonListener(item: MenuItem) : Boolean {
        return when(item.itemId) {
            R.id.exploreNavigation -> {
                Log.d("mylog", "explore!")
                true
            }
            R.id.searchNavigation -> {
                Log.d("mylog", "search!")
                true
            }
            R.id.favoriteNavigation -> {
                Log.d("mylog", "favorite!")
                true
            }
            else -> false
        }
    }

}