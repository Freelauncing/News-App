package com.newsapp.borislav.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.google.gson.JsonObject
import com.newsapp.borislav.api.ApiService
import com.newsapp.borislav.data.NewsDao
import com.newsapp.borislav.data.model.News
import com.newsapp.borislav.utils.Resource
import kotlinx.coroutines.*
import org.json.JSONObject
import retrofit2.Response
import retrofit2.http.Query

class MainRepository(private val service: ApiService,
                     private val newsDao: NewsDao,
                     private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO) {
    suspend fun getNews(
        countries:String,
        page_size:Int,
        lang:String,
        topic:String,
        page:Int) {

        GlobalScope.launch(ioDispatcher) {
            withContext(ioDispatcher) {
                var result: Response<JsonObject> = service.getNews(
                    countries,
                    page_size,
                    lang,
                    topic,
                    page
                )

                if (result.isSuccessful) {
                    val articles = result.body()!!.getAsJsonArray("articles")
                    for (i in 0 until articles.size()) {
                        val article = articles.get(i).asJsonObject
                        Log.v("HELLO",article.toString())

                        newsDao.insertNews(
                            News(
                                article.get("_id").asString,
                                checkAndGetString("media",article),
                                checkAndGetString("title",article),
                                checkAndGetString("author",article),
                                checkAndGetString("published_date",article),
                                checkAndGetString("link",article),
                                checkAndGetString("clean_url",article),
                                checkAndGetString("excerpt",article),
                                checkAndGetString("summary",article),
                                checkAndGetString("rights",article),
                                checkAndGetString("twitter_account",article),
                            )
                        )
                    }
                }
            }
        }
    }

    fun checkAndGetString(key:String,jsonObject: JsonObject):String{
        val obj = JSONObject(jsonObject.toString())
        if(obj.has(key)){
            if(obj.getString(key)!=null && !obj.getString(key).equals("null")){
                return obj.getString(key)
            }else{
                return ""
            }
        }
        return ""
    }



    fun observeNews(): LiveData<List<News>> {
        return newsDao.observeNews()
    }

    fun observeNewsById(id:String):LiveData<News>{
        return newsDao.observeNewsById(id)
    }

}