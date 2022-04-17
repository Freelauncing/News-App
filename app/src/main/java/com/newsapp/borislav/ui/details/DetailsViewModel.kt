package com.newsapp.borislav.ui.details

import android.widget.ImageView
import androidx.core.net.toUri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.bumptech.glide.Glide
import com.newsapp.borislav.data.model.News
import com.newsapp.borislav.repository.MainRepository
import com.newsapp.borislav.utils.Event
import com.newsapp.borislav.utils.NetworkHelper

class DetailsViewModel(private val mainRepository: MainRepository,
                       private val networkHelper: NetworkHelper
) : ViewModel() {

    private val _newsId = MutableLiveData<String>()

    private val _product = _newsId.switchMap { taskId ->
        mainRepository.observeNewsById(taskId)
    }
    val product: LiveData<News> = _product

    private val _dataLoading = MutableLiveData<Boolean>()
    val dataLoading: LiveData<Boolean> = _dataLoading
    private val _snackbarText = MutableLiveData<Event<String>>()
    val snackbarText: LiveData<Event<String>> = _snackbarText

    fun start(productId: String?) {
        // If we're already loading or already loaded, return (might be a config change)
        if (_dataLoading.value == true || productId == _newsId.value) {
            return
        }
        _dataLoading.value = true
        // Trigger the load
        _newsId.value = productId!!
    }


    private fun showSnackbarMessage(message: String) { //
        _snackbarText.value = Event(message)
    }

    fun ImageView.loadImagesWithGlideExt(url: String) {
        Glide.with(this)
            .load(url)
            .centerCrop()
            .into(this)
    }
}