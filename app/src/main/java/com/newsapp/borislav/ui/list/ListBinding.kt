package com.newsapp.borislav.ui.list

import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.newsapp.borislav.R
import com.newsapp.borislav.data.model.News
import java.text.SimpleDateFormat
import java.util.*

/**
 * [BindingAdapter]s for the [Product]s list.
 */
@BindingAdapter("app:items")
fun setItems(listView: RecyclerView, items: List<News>?) {
    items?.let {
        (listView.adapter as NewsListAdapter).submitList(items)
    }
}


@BindingAdapter("itemImage")
fun ImageView.setImage(item: String?) {
    if(!item.isNullOrEmpty()){
        try {
            val imgUri = item.toUri()
            item.let {
                Glide.with(context)
                    .load(imgUri)
                    .placeholder(R.drawable.ic_launcher_foreground)
                    .into(this)
            }
        }catch (e:Exception){

        }
    }
}

