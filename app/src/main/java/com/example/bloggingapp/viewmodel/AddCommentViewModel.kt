package com.example.bloggingapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bloggingapp.model.data.Comment
import com.example.bloggingapp.repository.PostRepository
import kotlinx.coroutines.launch

class AddCommentViewModel(private val repository: PostRepository) : ViewModel() {
    fun addNewComment(postId : Int, comment: Comment){
        viewModelScope.launch {
            repository.addComment(postId,comment)
        }

    }
}