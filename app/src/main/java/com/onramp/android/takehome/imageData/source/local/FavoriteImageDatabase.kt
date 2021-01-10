package com.onramp.android.takehome.imageData.source.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [FavoriteImage::class], version = 1)
abstract class FavoriteImageDatabase: RoomDatabase() {

    abstract fun imageDao(): FavoriteImageDao

    companion object{
        @Volatile
        private var INSTANCE: FavoriteImageDatabase? = null

        fun getDatabase(context: Context): FavoriteImageDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this){
                val instance = Room.databaseBuilder(
                        context.applicationContext,
                        FavoriteImageDatabase::class.java,
                        "favoriteImage"
                ).build()
                INSTANCE = instance
                return instance
            }

        }

    }

}