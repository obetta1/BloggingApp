package com.example.bloggingapp.model.data

import android.os.Parcelable

@Percelize
data class DetailPost(
    val title : String,
    val body : String,
    val postId : Int? = null
)

