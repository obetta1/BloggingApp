package com.example.bloggingapp.model.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DetailPost(
    val title : String,
    val body : String,
    val postId : Int? = null
):Parcelable

