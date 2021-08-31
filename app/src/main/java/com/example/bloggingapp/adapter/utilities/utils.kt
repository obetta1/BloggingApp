package com.example.bloggingapp.adapter.utilities

import android.app.DownloadManager
import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.SearchView
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import com.bumptech.glide.request.RequestOptions
import com.example.bloggingapp.R
import com.example.bloggingapp.adapter.MainPageAdapter
import com.example.bloggingapp.model.data.DetailPost
import com.example.bloggingapp.model.data.PostAndPhoto
import com.example.bloggingapp.ui.MainPageFragment
import retrofit2.http.Query

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

fun View.goto(action : NavDirections){
    this.findNavController().navigate(action)
}

fun View.goto(id:Int){
    this.findNavController().navigate(id)
}

  fun queryAllPosts(adapter : MainPageAdapter, searchView : SearchView){
      searchView.setOnQueryTextListener(object :SearchView.OnQueryTextListener{
          override fun onQueryTextSubmit(query: String? ): Boolean{
              return false
          }

          override fun onQueryTextChange(newText: String?): Boolean {
              adapter.filter.filter(newText)
              return true
          }
      })
  }

/* this is used to set button click listener for both
*the  fragment and the main fragment
 */
    fun createListenerForComments(view: View, post:PostAndPhoto, fragmentId : Int){
        val postDetail = DetailPost(post.post.title, post.post.body, post.post.id)
        when(fragmentId){
            1 -> {

            }
        }
    }
