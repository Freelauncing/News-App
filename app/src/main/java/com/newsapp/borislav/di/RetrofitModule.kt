package com.newsapp.borislav.di

import android.content.Context
import com.google.gson.GsonBuilder
import com.newsapp.borislav.api.ApiService
import com.newsapp.borislav.utils.NetworkHelper
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.core.scope.Scope
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private const val CONNECT_TIMEOUT = 1L
private const val WRITE_TIMEOUT = 1L
private const val READ_TIMEOUT = 1L
val RetrofitModule = module {
    single { Cache(androidApplication().cacheDir, 10L * 1024 * 1024) }
    single { GsonBuilder().create() }
    single { retrofitHttpClient() }
    single { retrofitBuilder() }
    single { provideNetworkHelper(androidContext()) }
    single { provideApiService(get()) }
    single {
        Interceptor { chain ->
            chain.proceed(chain.request().newBuilder().apply {
                header("Accept", "application/json")
                header("x-api-key","NF2-QiperYtNi93UmctE9pCLNH25Lo1Bt9FGaz_SFw4")
            }.build())
        }
    }
}

private fun provideNetworkHelper(context: Context) = NetworkHelper(context)

private fun provideApiService(retrofit: Retrofit): ApiService =
    retrofit.create(ApiService::class.java)

private fun Scope.retrofitBuilder(): Retrofit {
    return Retrofit.Builder()
        .baseUrl("https://api.newscatcherapi.com/v2/")
        .addConverterFactory(GsonConverterFactory.create(get()))
        .client(get())
        .build()
}


private fun Scope.retrofitHttpClient(): OkHttpClient {
    return OkHttpClient.Builder().apply {
        cache(get())
        connectTimeout(CONNECT_TIMEOUT, TimeUnit.MINUTES)
        writeTimeout(WRITE_TIMEOUT, TimeUnit.MINUTES)
        readTimeout(READ_TIMEOUT, TimeUnit.MINUTES)
        retryOnConnectionFailure(true)
        addInterceptor(get())
        addInterceptor(HttpLoggingInterceptor().apply {
            level = if (true) {
                HttpLoggingInterceptor.Level.HEADERS
                HttpLoggingInterceptor.Level.BODY
            }
            else {
                HttpLoggingInterceptor.Level.NONE
            }
        })
    }.build()
}