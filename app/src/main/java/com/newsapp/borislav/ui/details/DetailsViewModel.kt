package com.newsapp.borislav.ui.details

import androidx.lifecycle.ViewModel
import com.newsapp.borislav.repository.MainRepository
import com.newsapp.borislav.utils.NetworkHelper

class DetailsViewModel(private val mainRepository: MainRepository,
                       private val networkHelper: NetworkHelper
) : ViewModel() {
}