package com.example.bloggingapp.model.network

import com.example.bloggingapp.model.data.Comment
import com.example.bloggingapp.model.data.Photo
import com.example.bloggingapp.model.data.Post
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface JsonEndPoint {
    // makes a a call to the API to retrive all the post
    @GET("posts")
   suspend fun getAllPosts():List<Post>

   // this is used to retrieve all the photo
    @GET("photos")
   suspend fun getAllPhotos():List<Photo>
    //this is used to retrieve all the comment from the API
    @GET("comments")
   suspend fun getAllComments():List<Comment>

   //this is used to creat a new post on the API
   @POST("post")
   suspend fun createNewPost(@Body post:Post):Post

   //this is used to add new comment to the api
   @POST("post/{id}/comments")
   suspend fun addNewComment(@Path("id") id: Int, @Body comment: Comment):Comment

}