package com.onramp.android.takehome.imageData.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FavoriteImageDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addImage(image: FavoriteImage)

    @Query("SELECT * FROM favoriteImage")
    suspend fun getAllImages(): List<FavoriteImage>
}