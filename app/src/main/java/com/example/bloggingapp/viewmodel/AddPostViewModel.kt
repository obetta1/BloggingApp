package com.example.bloggingapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bloggingapp.model.data.Post
import com.example.bloggingapp.repository.PostRepository
import kotlinx.coroutines.launch

class AddPostViewModel(private val repository: PostRepository): ViewModel(){

    fun addNewPost(post: Post) { viewModelScope.launch { repository.addPost(post) }


}
}