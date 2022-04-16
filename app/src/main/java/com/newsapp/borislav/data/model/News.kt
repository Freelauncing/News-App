package com.newsapp.borislav.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "news")
data class News @JvmOverloads constructor(

    @PrimaryKey
    @ColumnInfo(name = "_id")
    var _id : String ,

    @ColumnInfo(name = "media")
    var media: String = "",

    @ColumnInfo(name = "title")
    var title: String = "",

    @ColumnInfo(name = "author")
    var author: String = "",

    @ColumnInfo(name = "published_date")
    var published_date: String = "",

    @ColumnInfo(name = "link")
    var link: String = "",

    @ColumnInfo(name = "clean_url")
    var clean_url: String = "",

    @ColumnInfo(name = "excerpt")
    var excerpt: String = "",

    @ColumnInfo(name = "summary")
    var summary: String = "",

    @ColumnInfo(name = "rights")
    var rights: String = "",

    @ColumnInfo(name = "twitter_account")
    var twitter_account: String = "",
)