package com.onramp.android.takehome.services

import android.app.Service
import android.content.Intent
import android.graphics.Bitmap
import android.media.MediaScannerConnection
import android.net.Uri
import android.os.Environment
import android.os.IBinder
import android.util.Log
import android.widget.Toast
import com.bumptech.glide.Glide
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream

class ImageDownloadService: Service() {
    private var downloadMap: HashMap<*, *> = hashMapOf<String, Map<String, String>>()

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        downloadMap = intent?.getSerializableExtra("downloadMap") as HashMap<*, *>
        CoroutineScope(Dispatchers.IO).launch { generateBitmaps() }
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    suspend fun generateBitmaps() {
        val downloadList = downloadMap.values

        for (imageObject in downloadList) {

            val imageObject = imageObject as Map<*, *>
            // Resource used: https://stackoverflow.com/questions/44761720/save-picture-to-storage-using-glide
            val bitmap = Glide.with(this)
                    .asBitmap()
                    .load(imageObject["url"])
                    .placeholder(android.R.drawable.progress_indeterminate_horizontal) // need placeholder to avoid issue like glide annotations
                    .error(android.R.drawable.stat_notify_error)
                    .submit()
                    .get()

            saveImage(bitmap, imageObject["name"] as String?, imageObject["description"] as String?)
        }
        withContext(Dispatchers.Main){
            Toast.makeText(applicationContext, "Downloads Finished!", Toast.LENGTH_LONG).show()
        }
    }

    private suspend fun saveImage(bitmap: Bitmap, name: String?, description: String?) {
        var savedImagePath: String? = null
        // make sure to use a valid file name(only include valid chars)
        val imageFileName: String = "JPEG_${name}_${description}.jpg"
        val storageDirectory = File("${applicationContext.getExternalFilesDir(Environment.DIRECTORY_PICTURES)}/galleryApp")

        var success = true
        if (!storageDirectory.exists()) {
            success = storageDirectory.mkdir()
        }

        if (success) {
            val imageFile = File(storageDirectory, imageFileName)
            savedImagePath = imageFile.absolutePath

            try {
                val fileOut: OutputStream = FileOutputStream(imageFile)
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOut)
                fileOut.flush()
                fileOut.close()

            } catch (e: Exception) {
                throw Exception("Error saving image: $e")
            }
            galleryAddPicture(savedImagePath)
        }
    }

    private suspend fun galleryAddPicture(imagePath: String?) {
        imagePath?.let { path ->
            val file = File(path)
            // Resource used: https://stackoverflow.com/questions/60203353/action-media-scanner-scan-filestring-is-deprecated
            MediaScannerConnection.scanFile(applicationContext, arrayOf(file.toString()), arrayOf(file.name), null)

        }
    }
}