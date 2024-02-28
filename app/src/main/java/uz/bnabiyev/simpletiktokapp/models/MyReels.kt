package uz.bnabiyev.simpletiktokapp.models

class MyReels {
    var id: Int? = null
    var title: String? = null
    var videoLink: String? = null

    constructor(id: Int?, title: String?, videoLink: String?) {
        this.id = id
        this.title = title
        this.videoLink = videoLink
    }

    constructor(title: String?, videoLink: String?) {
        this.title = title
        this.videoLink = videoLink
    }

}
