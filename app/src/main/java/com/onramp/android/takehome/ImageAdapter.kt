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
import com.google.android.material.switchmaterial.SwitchMaterial
import com.onramp.android.takehome.imageData.Image


class ImageAdapter(var context: Context?) : BaseAdapter() {
    private val imageList = ArrayList<Image>()
    private var switchVisible: Boolean = false

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

        // TODO: look up implementing placeholders
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

        val switchTag = listOf<String>(idx.toString(), url, image.user.name, image.alt_description)
        switchMaterial.tag = switchTag

        // set switchMaterial visibility
        if (switchVisible) switchMaterial.visibility = View.VISIBLE
        else switchMaterial.visibility = View.INVISIBLE

        return cardLayout
    }

    fun setData(images: ArrayList<Image>) {
        this.imageList.clear()
        this.imageList.addAll(images)
        notifyDataSetChanged()
    }

    fun setSwitchVisibility(visible: Boolean) {
        switchVisible = visible
        notifyDataSetChanged()
    }

}