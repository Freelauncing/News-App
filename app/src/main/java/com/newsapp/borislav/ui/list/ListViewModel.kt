package com.newsapp.borislav.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.newsapp.borislav.repository.MainRepository
import com.newsapp.borislav.utils.NetworkHelper
import com.newsapp.borislav.utils.Resource
import kotlinx.coroutines.launch
import org.json.JSONObject

class ListViewModel (private val mainRepository: MainRepository,
                     private val networkHelper: NetworkHelper
) : ViewModel() {

    private val _news = MutableLiveData<Resource<JSONObject>>()
    val news: LiveData<Resource<JSONObject>>
        get() = _news

    fun getNews(countries:String,
                 page_size:Int,
                 lang:String,
                 topic:String,
                 page:Int) {
        viewModelScope.launch {
            _news.postValue(Resource.loading(null))
            if (networkHelper.isNetworkConnected()) {
                mainRepository.getNews(countries,
                    page_size,
                    lang,
                    topic,
                    page).let {
                    if (it.isSuccessful) {
                        _news.postValue(Resource.success(it.body()))
                    } else _news.postValue(Resource.error(it.errorBody().toString(), null))
                }
            } else _news.postValue(Resource.error("No internet connection", null))
        }
    }

}