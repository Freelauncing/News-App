package com.newsapp.borislav.di

import com.newsapp.borislav.repository.MainRepository
import org.koin.dsl.module


val RepositoryModule = module {
    single {
        MainRepository(get())
    }
}