package com.example.bloggingapp

import android.app.Application
import com.example.bloggingapp.model.data.PostDatabase
import com.example.bloggingapp.model.network.JsonClient
import com.example.bloggingapp.repository.PostRepository
import com.example.bloggingapp.repository.PostRepositoryImpl

class App:Application() {
    override fun onCreate() {
        super.onCreate()
        instance = this
    }


    companion object{
        /**
         * This satisfies the dependencies of the repository and set instance and database private
         * since it's only the repository that is required outside the container
         */
        private lateinit var instance: App

        private val database : PostDatabase by lazy {
            PostDatabase.creatDatabase(instance)
        }

        /**
         * Exposing the value as an interface, entire app uses the repository without creating it
         * manually, keeps the initialization in one place and usage in another
         */

        val repository: PostRepository by lazy {
            PostRepositoryImpl(
                database.postDao(),
                database.photoDao(),
               database.commentDao(),
                JsonClient.getJsonEndPoint()
            )
        }
    }


}