package com.newsapp.borislav.di

import com.newsapp.borislav.data.database.NewsDatabase
import com.newsapp.borislav.data.database.getDatabase
import com.newsapp.borislav.data.database.provideNewsDao
import com.newsapp.borislav.repository.MainRepository
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module


val DatabaseModule = module {
    single {
        getDatabase(androidApplication())
    }
    single {
        provideNewsDao(get())
    }
}