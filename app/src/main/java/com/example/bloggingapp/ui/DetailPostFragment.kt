package com.example.bloggingapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavArgs
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.util.Util
import com.example.bloggingapp.App
import com.example.bloggingapp.R
import com.example.bloggingapp.ViewModelFactory
import com.example.bloggingapp.adapter.DetailPostAdapter
import com.example.bloggingapp.adapter.utilities.goto
import com.example.bloggingapp.model.data.Comment
import com.example.bloggingapp.model.data.DetailPost
import com.example.bloggingapp.viewmodel.DetailPostViewModel
import kotlinx.android.synthetic.main.fragment_detail_post.*


class DetailPostFragment : Fragment() {
   // private var args by navArgs<DetailPostFragmentArds>
    private lateinit var detailPost: DetailPost
    private lateinit var viewmodel: DetailPostViewModel
    private lateinit var detailPostAdapter: DetailPostAdapter
    private val repository by lazy { App.repository }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

       // detailPost = args.DetailPost
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
         val view = inflater.inflate(R.layout.fragment_detail_post, container, false)

        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModelFactory = ViewModelFactory(repository)
        viewmodel = ViewModelProvider(this, viewModelFactory)[DetailPostViewModel::class.java]

        detailPostAdapter = DetailPostAdapter(requireContext())
        postDetailRecycler.adapter = detailPostAdapter

        postTitle.text = detailPost.title
        postBody.text = detailPost.body

        fab.setOnClickListener {

           // val postDetail = ppost.title, post.post.body, post.post.id)
          var action = this.findNavController().navigate(R.id.action_detailPostFragment_to_addComentFragment)
            this.findNavController().navigate(action)

        }


        /**
         * Observes the commentList from the viewModel coming from the database and populates the
         * adapter accordingly
         */
        viewmodel.getCommentById(detailPost.postId).observe(viewLifecycleOwner,{
            detailPostAdapter.setUpComments(it as MutableList<Comment>)
        })
    }

}

private fun NavController.navigate(action: Unit) {

}



