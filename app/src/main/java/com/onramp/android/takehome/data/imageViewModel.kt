package com.onramp.android.takehome.data

data class imageViewModel(
        var title: String = "",
        var url: String = "",
        var favoriteStatus: Boolean = false
){

    fun toggleFavoriteStatus() {
        favoriteStatus = !favoriteStatus
    }

}
