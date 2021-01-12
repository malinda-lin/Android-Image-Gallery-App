package com.onramp.android.takehome.fragments

import android.app.Activity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.button.MaterialButton
import com.onramp.android.takehome.R
import com.onramp.android.takehome.explore.ExploreActivity

class TopAppBarFragment : Fragment(R.layout.fragment_top_app_bar) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val topAppBar = view.findViewById<MaterialToolbar>(R.id.topAppBar)
        topAppBar.setOnMenuItemClickListener{ menuItem -> setMenuItemListeners(menuItem) }
    }

    //  TODO: move this function to interface, TIP: interfaces can be passed as parameters, get rid of this fragment and use compound views
    private fun setMenuItemListeners(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_download -> {
                // Resource Used: https://www.youtube.com/watch?v=-DCZLKxmdIk&t=281s
                val hostActivity = activity as ExploreActivity // TODO: find way to dynamically cast to host activity activity.javaClass.name?
                hostActivity.setStartDownloadVisibility(true)
                hostActivity.setSwitchVisibility(true)
                true
            }
            R.id.action_about -> {
                // TODO: opens about activity
                true
            }
            R.id.action_settings -> {
                // TODO: opens settings activity
                true
            }
            else -> false
        }
    }

}