package com.newsapp.borislav.ui.list

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.newsapp.borislav.R
import com.newsapp.borislav.utils.Status
import org.json.JSONObject
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListFragment : Fragment() {

    private val listViewModel : ListViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpObservers()

        //listViewModel.getNews("US",100,"EN","news",1)
    }

    private fun setUpObservers() {
        listViewModel.news.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Status.SUCCESS -> {

                    it.data?.let { user -> parseUserValue(user) }
                }
                Status.LOADING -> {

                }
                Status.ERROR -> {

                }
            }
        })
    }

    private fun parseUserValue(user: JSONObject) {
        Log.v("Hello",user.toString())

    }
}