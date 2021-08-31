package com.example.bloggingapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.text.HtmlCompat

import androidx.recyclerview.widget.RecyclerView
import com.example.bloggingapp.R
import com.example.bloggingapp.adapter.utilities.getImages
import com.example.bloggingapp.adapter.utilities.loadImage
import com.example.bloggingapp.model.data.PostAndPhoto
import com.example.bloggingapp.ui.RecyclerViewOnClickListener

import java.util.*

class MainPageAdapter(
    private val context: Context,
    private val listener: RecyclerViewOnClickListener)
    : RecyclerView.Adapter<MainPageAdapter.MainPageViewHolder> (),
    Filterable {
    // this is used to j]hold te photos from the database
    private var postAndPhotos = mutableListOf<PostAndPhoto>()

    // this is used to save the initial list if post beforethe search operation is performed
    private var _post = mutableListOf<PostAndPhoto>()

    //this is the mage use to identify local userID
    private val postImage = getImages()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainPageAdapter.MainPageViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.post_item, parent, false)
    return  MainPageViewHolder(view)
    }

    override fun onBindViewHolder(holder: MainPageAdapter.MainPageViewHolder, position: Int) {
        holder.bind(postAndPhotos[position])

    }

    override fun getItemCount(): Int {
        var cur = postAndPhotos.size
        return cur
    }

    override fun getFilter(): Filter {
         return object :Filter(){
             override fun performFiltering(searchItem: CharSequence?): FilterResults {
                  val searchString = searchItem.toString()
                 //checkif the search entry is empty befor performing search
                 if(searchString.isEmpty()){
                     postAndPhotos = emptyArray<PostAndPhoto>().toMutableList()
                 }else{
                     postAndPhotos = _post
                     val initSearchedPost = mutableListOf<PostAndPhoto>()

                     for ( p in postAndPhotos){
                         if (p.post.title.toLowerCase(Locale.ROOT).trim().contains(searchString)){
                             initSearchedPost.add(p) }
                     }
                     postAndPhotos = initSearchedPost
                 }
                 val filterPosts = FilterResults()
                 filterPosts.values = postAndPhotos
                 return  filterPosts
             }

             override fun publishResults(searchItem : CharSequence?, result : FilterResults?) {
                  postAndPhotos = result?.values as MutableList<PostAndPhoto>
                 notifyDataSetChanged()
             }
         }
    }


    inner class MainPageViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        private val txtPostTitle = itemView.findViewById<TextView>(R.id.txtPostTitle)
        private val txtPostBody = itemView.findViewById<TextView>(R.id.txtPostBody)
        private val imgPostSmall = itemView.findViewById<ImageView>(R.id.postSmallImage)
        private val imgPostImage = itemView.findViewById<ImageView>(R.id.PostImage)
        private val txtUserId = itemView.findViewById<TextView>(R.id.userId)
        private val imgBookmark = itemView.findViewById<ImageView>(R.id.btnBookmark)
        private val btnComment = itemView.findViewById<ImageView>(R.id.btnComment)
        private val btnLike = itemView.findViewById<ImageView>(R.id.likebutton)
        private var collapseInto = false

        fun bind(post:PostAndPhoto){
            btnLike.setOnClickListener {
                when(post.post.liked){
                    false -> {
                        btnLike.setImageResource(R.drawable.ic_red_heart)
                        Toast.makeText(context, "You Liked This Photo", Toast.LENGTH_SHORT).show()
                    post.post.liked = true
                    }
                    true -> {
                        btnLike.setImageResource(R.drawable.heart_svgrepo_com)
                        post.post.liked = false
                    }
                }
            }

            // this line is used to set the image for each user
            imgPostSmall.setImageResource(postImage[post.post.userId - 1])

            // this is used to Sets the post title
            txtPostTitle.text = post.post.title

            // Sets the post body
            txtPostBody.text = HtmlCompat.fromHtml(
                context.getString(
                    R.string.placeholder_title_body, post.post.title, post.post.body
                ), HtmlCompat.FROM_HTML_MODE_LEGACY)

            // set the user id and the id
            txtUserId.text  = context.getString(R.string.placeholder_user_id,
                post.post.userId, post.post.id)

            //set the image for each post gotten from the API using the uri
            post.photo?.let { loadImage(context, it.url, imgPostImage) }

            // Sets a clickListener for the cardView which launches the postDetails activity
            btnComment.setOnClickListener {

                listener.onRecyclerViewClickListener(it, post)

            }

            // this is used to set the click listener the body of the post to expand or collapse it
            txtPostBody.setOnClickListener {
                if (collapseInto){
                    txtPostBody.maxLines  = 3
                    collapseInto = false
                }else{
                    txtPostBody.maxLines = Int.MAX_VALUE
                    collapseInto = true
                }

            }

            imgBookmark.setOnClickListener {
                when (post.post.bookmarked){
                    false-> {
                        imgBookmark.setImageResource(R.drawable.bookmark_svgrepo_com)
                        Toast.makeText(context, "${post.post.id} has been addeed to the bookmark",
                            Toast.LENGTH_SHORT).show()
                        post.post.bookmarked = true
                    }
                    true ->{
                        imgBookmark.setImageResource(R.drawable.bookmark_svgrepo_com_2)
                        post.post.bookmarked = false
                    }
                }
            }
        }

    }
    // the function is used to retrieve the post from the network
    // call and setthe post to the recyclerview
    fun postSetUp(posts :MutableList<PostAndPhoto>){
        postAndPhotos.clear()
        postAndPhotos.addAll(posts)
        this._post = posts
        notifyDataSetChanged()

    }


}
