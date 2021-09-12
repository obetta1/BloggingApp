package com.example.bloggingapp.ui

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.SearchView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.bloggingapp.App
import com.example.bloggingapp.R
import com.example.bloggingapp.ViewModelFactory
import com.example.bloggingapp.adapter.MainPageAdapter
import com.example.bloggingapp.utilities.createListenerForComments
import com.example.bloggingapp.utilities.queryAllPosts
import com.example.bloggingapp.model.data.PostAndPhoto
import com.example.bloggingapp.viewmodel.MainPageViewModel
import kotlinx.android.synthetic.main.fragment_search.*


class SearchFragment : Fragment() , RecyclerViewOnClickListener{

    private lateinit var viewModel: MainPageViewModel
    private lateinit var searchView: SearchView
    private lateinit var homeAdapter:  MainPageAdapter
    private val repository by lazy { App.repository }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       val view = inflater.inflate(R.layout.fragment_search, container, false)
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /**
         * Initializes the adapter and creates a listener to navigate to the postDetails
         * fragment
         */
        homeAdapter = MainPageAdapter(requireContext(), this)
        searchRecycler.adapter = homeAdapter

        // Makes use of the homepageViewModel
        val viewModelFactory = ViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory)[MainPageViewModel::class.java]

        /**
         * Observes for changes in the list of post and updates the list in the adapter accordingly
         * which in turn handles the ui display
         */
        viewModel.postAndPhotoList.observe(viewLifecycleOwner, {
            if (it != null) {
                homeAdapter.postSetUp(it as MutableList<PostAndPhoto>)
                searchRecycler.visibility = View.VISIBLE
            } else {
                Toast.makeText(context, "An error occured", Toast.LENGTH_SHORT).show()
            }
            progressBar2.visibility = View.GONE
        })
    }

    /**
     * Creates the option menu and queries the list in the adapter to filter searched character
     * sequence
     */
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_menu, menu)
        val searchItem = menu.findItem(R.id.app_bar_search)
        if (searchItem != null) {
            searchView = searchItem.actionView as SearchView
        }
        queryAllPosts(homeAdapter, searchView)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onRecyclerViewClickListener(view: View, post: PostAndPhoto) {
        val fragId = 2
        createListenerForComments(view, post, fragId)
    }


}