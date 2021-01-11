package com.onramp.android.takehome

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.google.android.material.switchmaterial.SwitchMaterial
import com.onramp.android.takehome.imageData.Image

class ImageAdapter : BaseAdapter{
    var context: Context? = null
    private var imageList: ArrayList<Image>

    constructor(context: Context?, imageList: ArrayList<Image>) : super() {
        this.context = context
        this.imageList = imageList
    }

    override fun getCount(): Int {
        return imageList.size
    }

    override fun getItem(position: Int): Any {
        return imageList[position]
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
        val switchMaterial = cardLayout.findViewById<SwitchMaterial>(R.id.downloadSwitchMaterial)

        textView.text = "Photo By ${image.user.name}"
        imageView.contentDescription = image.alt_description


        // loads imageView with URL
        val url = image.urls.small
        Glide.with(this.context!!)
                .load(url)
                .centerCrop()
                .override(400, 400)
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(imageView)

        imageView.tag = url

        val switchTag = listOf<String>(idx.toString(), url)
        switchMaterial.tag = switchTag

        return cardLayout
    }

}