package com.example.bloggingapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.example.bloggingapp.App
import com.example.bloggingapp.R
import com.example.bloggingapp.ViewModelFactory
import com.example.bloggingapp.adapter.MainPageAdapter
import com.example.bloggingapp.adapter.utilities.createListenerForComments

import com.example.bloggingapp.model.data.PostAndPhoto
import com.example.bloggingapp.viewmodel.MainPageViewModel
import kotlinx.android.synthetic.main.fragment_main_page.*

class MainPageFragment : Fragment(), RecyclerViewOnClickListener {

    private lateinit var mainPageAdapter: MainPageAdapter
    private lateinit var viewModel: MainPageViewModel
    private val repository by lazy { App.repository }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       val view = inflater.inflate(R.layout.fragment_main_page, container, false)
        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModelFactory = ViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory)[MainPageViewModel::class.java]

        // this is used to initialize the adapter and set a click to the view so
         // that it can nevigate to another fragment

        mainPageAdapter = MainPageAdapter(requireContext(), this)
        postRecycler.adapter = mainPageAdapter

        /**
         * this is used to Observes the changes in the list of post,
         * updates the list in the adapter
         * and  display the updated list of post on the ui
         */

        viewModel.postAndPhotoList.observe(viewLifecycleOwner, {
            if (it != null) {
                mainPageAdapter.postSetUp(it as MutableList<PostAndPhoto>)
                postRecycler.visibility = View.VISIBLE
            }else{
                Toast.makeText(context, "something went wrong", Toast.LENGTH_SHORT).show()
            }
            progressBar.visibility = View.GONE
        })

    }

    override fun onRecyclerViewClickListener(view: View, post: PostAndPhoto) {
        var fragmentId = 1
        createListenerForComments(view, post, fragmentId )
    }

}