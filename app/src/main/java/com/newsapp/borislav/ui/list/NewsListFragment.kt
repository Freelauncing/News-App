package com.newsapp.borislav.ui.list

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.google.gson.JsonObject
import com.newsapp.borislav.R
import com.newsapp.borislav.databinding.FragmentNewsListBinding
import com.newsapp.borislav.utils.EventObserver
import com.newsapp.borislav.utils.Status
import com.newsapp.borislav.utils.setupSnackbar
import org.json.JSONObject
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class NewsListFragment : Fragment() {

    private val newsListViewModel : NewsListViewModel by viewModel()

    private lateinit var viewDataBinding: FragmentNewsListBinding

    private lateinit var listAdapter: NewsListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_news_list, container, false)

        viewDataBinding = FragmentNewsListBinding.bind(view).apply {
            this.viewmodel = newsListViewModel
        }

        viewDataBinding.lifecycleOwner = this.viewLifecycleOwner

        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setHasOptionsMenu(true)

        setupSnackbar()

        setUpObservers()

        setupListAdapter()

        //newsListViewModel.getNews("US",100,"EN","news",1)
    }

    private fun setupSnackbar() {
        view?.setupSnackbar(this, newsListViewModel.snackbarText, Snackbar.LENGTH_SHORT)
    }


    override fun onPause() {
        super.onPause()
        if(newsListViewModel.snackbarText.hasActiveObservers())
            newsListViewModel.snackbarText.removeObservers(this)
    }

    override fun onResume() {
        super.onResume()
        if(!newsListViewModel.snackbarText.hasActiveObservers())
            setupSnackbar()
    }

    private fun setupListAdapter() {
        val viewModel = viewDataBinding.viewmodel
        if (viewModel != null) {
            listAdapter = NewsListAdapter(viewModel)
            viewDataBinding.newsListRecyclerView.adapter = listAdapter
        } else {
            Timber.w("ViewModel not initialized when attempting to set up adapter.")
        }
    }

    private fun openTaskDetails(newsID: String) {
        val action = NewsListFragmentDirections.actionListFragmentToDetailsFragment(newsID)
        findNavController().navigate(action)
    }

    private fun setUpObservers() {

        newsListViewModel.openTaskEvent.observe(viewLifecycleOwner, EventObserver {
            openTaskDetails(it)
        })

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here.
        val id = item.getItemId()

        if (id == R.id.refresh) {
            newsListViewModel.refresh()
            Toast.makeText(requireContext(), "Fetching Latest News", Toast.LENGTH_LONG).show()
            return true
        }
        return super.onOptionsItemSelected(item)

    }
}