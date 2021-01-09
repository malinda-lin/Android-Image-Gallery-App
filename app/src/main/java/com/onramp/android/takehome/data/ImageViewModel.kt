package com.onramp.android.takehome.data

data class ImageViewModel(
        val alt_description: String,
        val created_at: String,
        val description: String,
        val id: String,
        val links: Links,
        val urls: Urls,
        val user: User
)
