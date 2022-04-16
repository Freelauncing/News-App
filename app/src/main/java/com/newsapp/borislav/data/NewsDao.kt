package com.newsapp.borislav.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.newsapp.borislav.data.model.News

@Dao
interface NewsDao {

    @Query("SELECT * FROM news")
    fun observeNewss(): LiveData<List<News>>

    @Query("SELECT * FROM news")
    suspend fun getNews(): List<News>

    @Query("SELECT * FROM news WHERE _id = :NewsId")
    fun observeNewsById(NewsId: Long): LiveData<News>

    @Query("SELECT * FROM news WHERE _id = :NewsId")
    suspend fun getNewsById(NewsId: Long): News?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNews(news: News)

    @Update
    suspend fun updateNews(product: News): Int

    @Query("DELETE FROM news WHERE _id = :newId")
    suspend fun deleteNewsById(newId: Long): Int

    @Query("DELETE FROM news")
    suspend fun deleteNews()

}