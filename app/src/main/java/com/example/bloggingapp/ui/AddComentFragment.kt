package com.example.bloggingapp.ui

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.bloggingapp.App
import com.example.bloggingapp.R
import com.example.bloggingapp.ViewModelFactory
import com.example.bloggingapp.model.data.Comment
import com.example.bloggingapp.model.data.DetailPost
import com.example.bloggingapp.utilities.goto
import com.example.bloggingapp.viewmodel.AddCommentViewModel
import kotlinx.android.synthetic.main.fragment_add_coment.*


class AddComentFragment : Fragment() {
    private lateinit var viewModel : AddCommentViewModel
    private lateinit var detailPost : DetailPost
    private val args by navArgs<AddComentFragmentArgs>()
    private val repository by lazy { App.repository }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =inflater.inflate(R.layout.fragment_add_coment, container, false)

        // get the detail from the detAILPOST TO GET ACCESS TO THE COMMENTID
        detailPost = args.detailPost
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModelFactory = ViewModelFactory(repository)
        viewModel = ViewModelProvider(this,viewModelFactory)[AddCommentViewModel::class.java]

        /**
         * when the button is clicked , a new comment is created and then navigate to the DetailPost
         * where new comment is viewed
         */

        btnCreateComment.setOnClickListener {
            viewModel.addNewComment(detailPost.postId!!, creatComment())
            Handler(Looper.getMainLooper()).postDelayed({
            val action = AddComentFragmentDirections.actionAddComentFragmentToDetailPostFragment(detailPost)
            it.goto(action)
            }, 1100)
        }
    }
    // this function ids pass to the viewModel as a parameter to the addComment methode
   private fun creatComment():Comment{
       val commentBody = edtCommentBody.text.toString()
        val commentName = txtCommentName.text.toString()
        val commentEmail = txtCommentEmail.text.toString()
        return Comment(body = commentBody, postId = detailPost.postId!!, email = commentEmail, name = commentName)
   }
}

