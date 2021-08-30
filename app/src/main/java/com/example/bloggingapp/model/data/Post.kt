package com.example.bloggingapp.model.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Post(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    val userId: Int,
    val body: String,
    val title: String,
    var liked : Boolean = false,
    var bookmarked : Boolean = false,

    )
