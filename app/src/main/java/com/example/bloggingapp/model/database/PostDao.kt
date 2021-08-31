package com.example.bloggingapp.model.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.bloggingapp.model.data.Post
import com.example.bloggingapp.model.data.PostAndPhoto
import retrofit2.http.Query

@Dao
interface PostDao {
    //return a list post and photos
    @androidx.room.Query("SELECT * FROM Post ORDER BY id DESC")
    fun getPostAndPhotos() : LiveData<List<PostAndPhoto>>

    //this is used to add a new post gotten from thye network call to tyhe database
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addPost(post: Post)

    // add the initial post from tyhe network
    @Insert(onConflict =  OnConflictStrategy.REPLACE)
    fun addAllPost(post: List<Post>)


}
