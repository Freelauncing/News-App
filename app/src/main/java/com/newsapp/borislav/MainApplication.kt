package com.newsapp.borislav

import android.app.Application
import com.newsapp.borislav.di.DatabaseModule
import com.newsapp.borislav.di.RepositoryModule
import com.newsapp.borislav.di.RetrofitModule
import com.newsapp.borislav.di.ViewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApplication :Application(){

    companion object {
        lateinit var instance: MainApplication
            private set
    }

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MainApplication)
            modules(listOf(
                DatabaseModule,
                ViewModelModule,
                RepositoryModule,
                RetrofitModule,
            ))

        }

        instance = this
    }
}