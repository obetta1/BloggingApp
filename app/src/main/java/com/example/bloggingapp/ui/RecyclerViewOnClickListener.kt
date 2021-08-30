package com.example.bloggingapp.ui

import android.view.View
import com.example.bloggingapp.model.data.Post
import com.example.bloggingapp.model.data.PostAndPhoto

interface RecyclerViewOnClickListener {
    // this function is used to listen to a click action on the recycler view
    fun onRecyclerViewClickListener(View: View, post: PostAndPhoto)
}