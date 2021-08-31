package com.example.bloggingapp.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.bloggingapp.model.data.Comment
import com.example.bloggingapp.model.data.Post
import com.example.bloggingapp.model.data.PostAndPhoto
import com.example.bloggingapp.model.database.CommentDao
import com.example.bloggingapp.model.database.PhotoDao
import com.example.bloggingapp.model.database.PostDao
import com.example.bloggingapp.model.network.JsonEndPoint
import com.google.gson.annotations.JsonAdapter
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext

/**
 * This is class used as a repository class to provide access to the API endpoint, manages queries and communicates with the
 * remote database according to request from the viewModel populates the database and retrieves
 * data from the database
 */

class PostRepositoryImpl (private var postDao: PostDao,
                          private val photoDao: PhotoDao,
                          private val commentDao: CommentDao,
                          private val jsonEndpoint: JsonEndPoint) : PostRepository {

    // Retrieves post from the network and adds to the database
    override suspend fun addAllPost() {
        withContext(IO) {
            try {
                val posts = jsonEndpoint.getAllPosts()
                postDao.addAllPost(posts)
            } catch (e: Exception) {
                Log.d("Network Error", "${e.message}")
            }
        }
    }

    // Retrieves photos from the network and adds to the database
    override suspend fun addAllPhoto() {
        withContext(IO) {
            try {
                val photos = jsonEndpoint.getAllPhotos()
                photoDao.addAllPhoto(photos)
            } catch (e: Exception) {
                Log.d("Network Error", "${e.message}")
            }

        }
    }

    // Retrieves comments from the network and adds to the database
    override suspend fun addAllComment() {
        withContext(IO) {
            try {
                val comments = jsonEndpoint.getAllComments()
                commentDao.addAllComment(comments)
            } catch (e: Exception) {
                Log.d("Network Error", "${e.message}")
            }

        }
    }

    // Creates and retrieves new post from the network and adds to the database
    override suspend fun addPost(post: Post) {
        withContext(IO) {
            try {
                val newPost = jsonEndpoint.createNewPost(post)
                newPost.id = null
                postDao.addPost(newPost)
            } catch (e: Exception) {
                Log.d("Network Error", "${e.message}")
            }

        }
    }

    // Creates and retrieves new comment from the network and adds to the database
    override suspend fun addComment(id: Int, comment: Comment) {
        withContext(IO) {
            try {
                val newComment = jsonEndpoint.addNewComment(id, comment)
                newComment.id = null
                commentDao.addNewComment(newComment)
            } catch (e: Exception) {
                Log.d("Network Error", "${e.message}")
            }

        }
    }

    // Retrieves a combination of the post and photos from the database
    override fun getPostAndPhoto(): LiveData<List<PostAndPhoto>> {
        return postDao.getPostAndPhotos()
    }

    // Retrieves comment from the database based on the post Id
    override fun getCommentById(postId: Int?): LiveData<List<Comment>> {
        return commentDao.getCommentById(postId)
    }


}