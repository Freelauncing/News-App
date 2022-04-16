package com.newsapp.borislav.di

import com.newsapp.borislav.ui.details.DetailsViewModel
import com.newsapp.borislav.ui.list.ListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val ViewModelModule = module {
    viewModel {
        ListViewModel(get(),get())
    }
    viewModel {
        DetailsViewModel(get(),get())
    }
}