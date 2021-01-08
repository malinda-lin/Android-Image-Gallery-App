package com.onramp.android.takehome.data

class ImageViewModel {
    var id: String? = null
    var alt_description: String? = null
    var description: String? = null
    var url: String? = null
    var user_name: String? = null
    var views: Int? = null
    var likes: Int? = null
    var download_link: String? = null
    var download_location: String? = null

    constructor(id: String?, alt_description: String?, description: String?, url: String?, user_name: String?, views: Int?, likes: Int?, download_link: String?, download_location: String?) {
        this.id = id
        this.alt_description = alt_description
        this.description = description
        this.url = url
        this.user_name = user_name
        this.views = views
        this.likes = likes
        this.download_link = download_link
        this.download_location = download_location
    }
}