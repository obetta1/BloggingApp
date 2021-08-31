package com.example.bloggingapp.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.bloggingapp.R
import com.example.bloggingapp.model.data.Comment

class DetailPostAdapter(private var context: Context):
    RecyclerView.Adapter<DetailPostAdapter.CommentViewHolder>(){


        // This will hold the list of comments from the database
        private var comments = mutableListOf<Comment>()
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DetailPostAdapter.CommentViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.detail_post_item, parent, false)
        return CommentViewHolder(view)
    }

    override fun onBindViewHolder(holder: DetailPostAdapter.CommentViewHolder, position: Int) {
        holder.bind(comments[position])
    }

    override fun getItemCount() =comments.size


    // ViewHolder class for the recyclerView comments
    inner class CommentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val txtCommentNameEmail = itemView.findViewById<TextView>(R.id.txtCommentNameEmail)
        private val txtCommentBody = itemView.findViewById<TextView>(R.id.txtCommentBody)

        fun bind(comment: Comment) {
            // Sets the text of the comment body
            txtCommentBody.text = comment.body

            // Sets the name and email text for the user that is commenting
            txtCommentNameEmail.text = HtmlCompat.fromHtml(
                context.getString(
                    R.string.placeholder_comment_name_email,
                    comment.name,
                    comment.email
                ), HtmlCompat.FROM_HTML_MODE_LEGACY
            )
        }
    }


    /*
    Receives the comments retrieved from the database and sets it
    up here in the adapter
     */
    fun setUpComments(comments: MutableList<Comment>) {
        this.comments.clear()
        this.comments.addAll(comments)
        Log.d("PostAdapter", "setUpComments: $comments")
        notifyDataSetChanged()
    }

    /*
    Gets the newly created comment from the network added to the database and adds
    it to the list of comment already present in the recycler view
     */
    fun addNewComment(comment: Comment) {
        comments.add(comment)
        notifyDataSetChanged()
    }

}