package com.onramp.android.takehome

import android.content.Context
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
        var image = this.imageList[idx]
        var inflater = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        var cardLayout = inflater.inflate(R.layout.image_card_view, null)

        var imageView = cardLayout.findViewById<ImageView>(R.id.cardImageView)
        var textView = cardLayout.findViewById<TextView>(R.id.cardTextView)

        textView.text = image.id

        var url = image.url
        Glide.with(this.context!!)
                .load(url)
                .centerCrop()
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(imageView)

        return cardLayout
    }

}