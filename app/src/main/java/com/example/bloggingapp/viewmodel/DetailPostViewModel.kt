package com.example.bloggingapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.bloggingapp.model.data.Comment
import com.example.bloggingapp.repository.PostRepository

class DetailPostViewModel(private val repository: PostRepository) : ViewModel() {
    fun getCommentById(postId : Int?) : LiveData<List<Comment>> {
        return repository.getCommentById(postId)
    }
}