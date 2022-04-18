package com.newsapp.borislav.data.database

import android.app.Application
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.newsapp.borislav.data.NewsDao
import com.newsapp.borislav.data.model.News

@Database(entities = [News::class], version = 1, exportSchema = false)
abstract class NewsDatabase : RoomDatabase() {

    abstract fun newsDao(): NewsDao

}

private lateinit var INSTANCE: NewsDatabase


fun getDatabase(application: Application): NewsDatabase {
    synchronized(NewsDatabase::class.java){
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(
                application,
                NewsDatabase::class.java,
                "news_db")
                .fallbackToDestructiveMigration()
                .build()
        }
    }
    return INSTANCE
}

fun provideNewsDao(database: NewsDatabase): NewsDao {
    return  database.newsDao()
}