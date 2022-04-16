package com.newsapp.borislav.api

import org.json.JSONObject
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {

    @GET("latest_headlines")
    suspend fun getNews(@Query("countries") countries:String,
                        @Query("page_size") page_size:Int,
                        @Query("lang") lang:String,
                        @Query("topic") topic:String,
                        @Query("page") page:Int): Response<JSONObject>
}