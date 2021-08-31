package com.example.bloggingapp.model.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.bloggingapp.model.database.CommentDao
import com.example.bloggingapp.model.database.PhotoDao
import com.example.bloggingapp.model.database.PostDao

/*
   * this class is usedto creat a database of tables of post, photos , and comment.
   *  it holds the reference of the Dao which is used to obtain the data from the database
   * */
@Database(entities = [Post::class, Photo::class, Comment::class] ,version = 1, exportSchema = false )

abstract class PostDatabase :RoomDatabase(){

    abstract fun postDao() : PostDao

    abstract fun photoDao() : PhotoDao

    abstract fun commentDao() : CommentDao

    companion object{
        @Volatile
        private var  INSTANCE : PostDatabase? = null
        private const val DATABASE_NAME = "posts"

        // Creates the post database when called and returns an instance of the postDatabase class
    fun creatDatabase(context: Context): PostDatabase{
        return INSTANCE?: synchronized(this){
            val instance = Room.databaseBuilder(
                context.applicationContext,
                PostDatabase::class.java,
                DATABASE_NAME
            ).build()

            INSTANCE = instance
            instance
        }
    }
}}