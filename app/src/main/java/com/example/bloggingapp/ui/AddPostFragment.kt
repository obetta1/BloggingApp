package com.example.bloggingapp.ui

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavDirections
import com.example.bloggingapp.App
import com.example.bloggingapp.R
import com.example.bloggingapp.ViewModelFactory
import com.example.bloggingapp.utilities.validatePostInput
import com.example.bloggingapp.model.data.Post
import com.example.bloggingapp.utilities.goto
import com.example.bloggingapp.viewmodel.AddPostViewModel
import kotlinx.android.synthetic.main.fragment_add_post2.*


class AddPostFragment : Fragment() {

    private lateinit var viewModel: AddPostViewModel
    private val repository by lazy { App.repository }


    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?,
                               savedInstanceState: Bundle? ): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_add_post2, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModelFactory = ViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory)[AddPostViewModel::class.java]

        /**
         * On click of the button new comment is created and user is navigated to the postDetails
         * where the created comment is viewed
         */


        btnCreatePost.setOnClickListener {
            val post = createPost()
            if (post != null){
                viewModel.addNewPost(post)
                Handler(Looper.getMainLooper()).postDelayed({
                   val action = AddPostFragmentDirections.actionAddPostFragment2ToMainPageFragment()
                    it.goto(action)
                }, 1000)
            }else{
                Toast.makeText(context, "please Enter text post", Toast.LENGTH_SHORT).show()
            }

        }

    }

    private fun createPost(): Post? {
        val postTitle = edtNewPostTitle.text.toString()
        val postBody = edtNewPostBody.text.toString()
        val postUserId = 11
        return when {
            validatePostInput(postTitle,postBody) -> {
                Toast.makeText(context, "post add correctly", Toast.LENGTH_SHORT).show()
                Post(title = postTitle, userId = postUserId, body = postBody)
            }
            else -> null
        }

    }




}