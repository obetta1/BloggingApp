package com.example.bloggingapp.model.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.bloggingapp.model.data.Comment

@Dao
interface CommentDao {
    // get the comment from the API
    @Query("SELECT* FROM Comment WHERE postId = :postId")
    fun getCommentById(postId : Int?) : LiveData<List<Comment>>

    // add all the comment from the API to the comment list
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addAllComment(comment :List<Comment>)

    // add a new comment to the database
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addNewComment(comment:Comment)
}

