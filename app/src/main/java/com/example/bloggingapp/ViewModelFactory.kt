package com.example.bloggingapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.bloggingapp.repository.PostRepository

class ViewModelFactory(private val repository : PostRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return modelClass.getConstructor(PostRepository::class.java)
                .newInstance(repository)
        }
}