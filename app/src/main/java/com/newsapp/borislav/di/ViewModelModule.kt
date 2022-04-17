package com.newsapp.borislav.di

import com.newsapp.borislav.ui.details.DetailsViewModel
import com.newsapp.borislav.ui.list.NewsListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val ViewModelModule = module {
    viewModel {
        NewsListViewModel(get(),get())
    }
    viewModel {
        DetailsViewModel(get(),get())
    }
}