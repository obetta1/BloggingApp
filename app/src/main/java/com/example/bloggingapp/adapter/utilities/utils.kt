package com.example.bloggingapp.adapter.utilities

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import com.bumptech.glide.request.RequestOptions
import com.example.bloggingapp.R

/*this function is used to load  image into a given
*imageview, generate a url using the
*GlideUrl from the given image uri
* and glide then loads the image into the view
*/
fun loadImage(context: Context, imgUrl: String, view: ImageView) {
    val url = GlideUrl(imgUrl, LazyHeaders.Builder()
            .addHeader("User-Agent", "your-user-agent")
            .build())

    Glide.with(context)
        .load(url).apply {
            RequestOptions()
                .placeholder(R.drawable.loading_status_animation)
                .error(R.drawable.ic_error_image) }.into(view)
}

// Holds a list of local images used to represent a particular user
fun getImages(): MutableList<Int> {
    return mutableListOf(
        R.drawable.hermionedhface,
        R.drawable.danearys,
        R.drawable.oliver,
        R.drawable.carlsen_opt,
        R.drawable.naomi,
        R.drawable.jack,
        R.drawable.homeland,
        R.drawable.anthony,
        R.drawable.lebron,
        R.drawable.jack,
        R.drawable.serena
    )
}