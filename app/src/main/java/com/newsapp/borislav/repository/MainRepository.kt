package com.newsapp.borislav.repository

import com.newsapp.borislav.api.ApiService
import com.newsapp.borislav.data.NewsDao
import retrofit2.http.Query

class MainRepository(private val service: ApiService,private val newsDao: NewsDao) {
    suspend fun getNews(
        countries:String,
        page_size:Int,
        lang:String,
        topic:String,
        page:Int) =  service.getNews(countries,
                                    page_size,
                                    lang,
                                    topic,
                                    page)
}