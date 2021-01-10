package com.onramp.android.takehome.imageData.source.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.onramp.android.takehome.imageData.Links
import com.onramp.android.takehome.imageData.Urls
import com.onramp.android.takehome.imageData.User

@Entity
data class FavoriteImage(
        @PrimaryKey(autoGenerate = true) val id: Int,
        @ColumnInfo(name = "alt_description") val alt_description: String?,
        @ColumnInfo(name = "description") val description: String?,
        @ColumnInfo(name = "url") val url: String?,
        @ColumnInfo(name = "user_name") val user_name: String?
)
