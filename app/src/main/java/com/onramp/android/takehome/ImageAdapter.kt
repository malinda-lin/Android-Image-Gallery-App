package com.onramp.android.takehome

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.onramp.android.takehome.data.ImageViewModel

class ImageAdapter : BaseAdapter{
    var context: Context? = null
    var imageList: ArrayList<ImageViewModel>

    constructor(context: Context?, imageList: ArrayList<ImageViewModel>) : super() {
        this.context = context
        this.imageList = imageList
    }

    override fun getCount(): Int {
        return imageList.size
    }

    override fun getItem(position: Int): Any {
        return position
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(idx: Int, convertView: View?, parent: ViewGroup?): View {
        val image = this.imageList[idx]

        val inflater = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val cardLayout = inflater.inflate(R.layout.image_card_view, null)

        val imageView = cardLayout.findViewById<ImageView>(R.id.cardImageView)
        val textView = cardLayout.findViewById<TextView>(R.id.cardTextView)

        textView.text = image.id

        // loads imageView with URL
        val url = image.urls.small
        Glide.with(this.context!!)
                .load(url)
                .centerCrop()
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(imageView)

        return cardLayout
    }

}