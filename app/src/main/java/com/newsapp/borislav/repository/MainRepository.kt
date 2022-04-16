package com.newsapp.borislav.repository

import com.newsapp.borislav.api.ApiService
import retrofit2.http.Query

class MainRepository(private val service: ApiService) {
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