package com.newsapp.borislav.ui.list

import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.newsapp.borislav.data.model.News

/**
 * [BindingAdapter]s for the [Product]s list.
 */
@BindingAdapter("app:items")
fun setItems(listView: RecyclerView, items: List<News>?) {
    items?.let {
        (listView.adapter as NewsListAdapter).submitList(items)
    }
}

@BindingAdapter("app:convertToString")
fun convertLongToString(textView: TextView, value:Long) {
    textView.text = value.toString() + " items"
}

@BindingAdapter("itemImage")
fun ImageView.setImage(item: String) {
    val imgUri = item.toUri()
    item?.let {
        Glide.with(context)
            .load(imgUri)
            .into(this)
    }
}