package com.example.bloggingapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.bloggingapp.App
import com.example.bloggingapp.R
import com.example.bloggingapp.ViewModelFactory
import com.example.bloggingapp.adapter.DetailPageAdapter
import com.example.bloggingapp.model.data.Comment
import com.example.bloggingapp.model.data.DetailPost
import com.example.bloggingapp.utilities.goto
import com.example.bloggingapp.viewmodel.DetailPostViewModel
import kotlinx.android.synthetic.main.fragment_detail_post.*


class DetailPostFragment : Fragment() {
    private val args by navArgs<DetailPostFragmentArgs>()
    private lateinit var detailPost: DetailPost
    private lateinit var viewmodel: DetailPostViewModel
    private lateinit var detailPostAdapter: DetailPageAdapter
    private val repository by lazy { App.repository }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
         val view = inflater.inflate(R.layout.fragment_detail_post, container, false)
        detailPost = args.detailPost
        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModelFactory = ViewModelFactory(repository)
        viewmodel = ViewModelProvider(this, viewModelFactory)[DetailPostViewModel::class.java]

    //initailize the adapter and the set it to the recyclerView
        detailPostAdapter = DetailPageAdapter(requireContext())
        postDetailRecycler.adapter = detailPostAdapter
        /**
         *  populate the various views usint the detailpost and sets an onclick listener to the
         * floating action button to navigate to another fragment to add a new comment to the post
         */
        postTitle.text = detailPost.title
        postBody.text = detailPost.body

        fab.setOnClickListener {
            val action = DetailPostFragmentDirections.actionDetailPostFragmentToAddComentFragment(detailPost)
            it.goto(action)
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




