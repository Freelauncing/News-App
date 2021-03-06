package com.newsapp.borislav.ui.list

import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ceylonlabs.imageviewpopup.ImagePopup
import com.newsapp.borislav.R
import com.newsapp.borislav.data.model.News
import com.newsapp.borislav.databinding.ItemListNewsBinding


class NewsListAdapter(private val viewModelNews: NewsListViewModel) : ListAdapter<News, NewsListAdapter.ViewHolder>(TaskDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(viewModelNews, item)
    }


    class ViewHolder private constructor(val binding: ItemListNewsBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(viewModelNews: NewsListViewModel, item: News) {

            binding.productImage.setOnClickListener {
                val imagePopup = ImagePopup(binding.productImage.context)
                if(!item.media.isNullOrEmpty()){
                    try {
                        imagePopup.initiatePopupWithGlide(item.media) // Load Image from Drawable
                        imagePopup.viewPopup();
                    }catch (e:Exception){

                    }
                }
            }

            binding.viewmodel = viewModelNews
            binding.news = item
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemListNewsBinding.inflate(layoutInflater, parent, false)

                return ViewHolder(binding)
            }
        }
    }


}

class TaskDiffCallback : DiffUtil.ItemCallback<News>() {
    override fun areItemsTheSame(oldItem: News, newItem: News): Boolean {
        return oldItem._id == newItem._id
    }

    override fun areContentsTheSame(oldItem: News, newItem: News): Boolean {
        return oldItem == newItem
    }
}
