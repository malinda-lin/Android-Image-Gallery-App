package com.onramp.android.takehome.services

import android.app.Service
import android.content.Intent
import android.os.IBinder

class ImageDownloadService: Service() {
    // this function is how the service runs
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return super.onStartCommand(intent, flags, startId)
    }

    // this function will stop the service
    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onBind(intent: Intent?): IBinder? {
        TODO("Not yet implemented")
    }

}