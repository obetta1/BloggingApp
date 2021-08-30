package com.example.bloggingapp.repository

import androidx.lifecycle.LiveData
import com.example.bloggingapp.model.data.Comment
import com.example.bloggingapp.model.data.Post
import com.example.bloggingapp.model.data.PostAndPhoto

/**
 * Contain declaration of abstract methods which is implemented by the main repository
 * class
 */
interface PostRepository {
    suspend fun addAllPost()

    suspend fun addAllPhoto()

    suspend fun addAllComment()

    suspend fun addComment(id: Int, comment: Comment)

    suspend fun addPost(post: Post)

    fun getPostAndPhoto(): LiveData<List<PostAndPhoto>>

    fun getCommentById(postId: Int?): LiveData<List<Comment>>
}
