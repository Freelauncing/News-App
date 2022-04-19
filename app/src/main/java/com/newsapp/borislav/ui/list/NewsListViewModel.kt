package com.newsapp.borislav.ui.list

import android.util.Log
import androidx.lifecycle.*
import com.google.gson.JsonObject
import com.newsapp.borislav.data.model.News
import com.newsapp.borislav.repository.MainRepository
import com.newsapp.borislav.utils.Event
import com.newsapp.borislav.utils.NetworkHelper
import com.newsapp.borislav.utils.Resource
import kotlinx.coroutines.launch
import org.json.JSONObject

class NewsListViewModel (private val mainRepository: MainRepository,
                         private val networkHelper: NetworkHelper
) : ViewModel() {

    private val _country = "PK"
    private val _pagesize = 100
    private val _lang = "EN"
    private val _topic = "news"
    private val _page = 1

    private val _dataLoading = MutableLiveData<Boolean>()
    val dataLoading: LiveData<Boolean> = _dataLoading

    private val _snackbarText = MutableLiveData<Event<String>>()
    val snackbarText: LiveData<Event<String>> = _snackbarText

    private val _openTaskEvent = MutableLiveData<Event<String>>()
    val openTaskEvent: LiveData<Event<String>> = _openTaskEvent


    fun showSnackbarMessage(message: String) { //
        _snackbarText.value = Event(message)
    }

    fun openTask(taskId: String) { //
        _openTaskEvent.value = Event(taskId)
    }

    private val _forceUpdate = MutableLiveData<Boolean>(false)

    private val _items: LiveData<List<News>> = _forceUpdate.switchMap { forceUpdate ->
        if (forceUpdate) {
            _dataLoading.value = true
            if(networkHelper.isNetworkConnected()) {
                viewModelScope.launch {
                    mainRepository.getNews(_country, _pagesize, _lang, _topic, _page)
                    _dataLoading.value = false
                }
            }else{
                _dataLoading.value = false
                showSnackbarMessage("No Internet Connected")
            }
        }
        mainRepository.observeNews()
    }

    val news: LiveData<List<News>> = _items

    // This LiveData depends on another so we can use a transformation.
    val empty: LiveData<Boolean> = Transformations.map(_items) {
        it.isEmpty()
    }

    fun refresh() { //
        _forceUpdate.value = true
    }
}