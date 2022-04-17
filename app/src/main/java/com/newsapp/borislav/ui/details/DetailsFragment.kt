package com.newsapp.borislav.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.snackbar.Snackbar
import com.newsapp.borislav.R
import com.newsapp.borislav.data.model.News
import com.newsapp.borislav.databinding.FragmentDetailsBinding
import com.newsapp.borislav.utils.setupSnackbar
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat
import java.util.*


class DetailsFragment : Fragment() {

    private val args: DetailsFragmentArgs by navArgs()

    private lateinit var viewDataBinding: FragmentDetailsBinding

    private val viewModel : DetailsViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_details, container, false)

        viewDataBinding = FragmentDetailsBinding.bind(view).apply {
            this.viewmodel = viewModel
        }

        viewDataBinding.lifecycleOwner = this.viewLifecycleOwner

        viewModel.start(args.newsId)

        return viewDataBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setupSnackbar()

        setUpListeners()

    }

    private fun setUpListeners() {

    }

    private fun setupSnackbar() {
        view?.setupSnackbar(this, viewModel.snackbarText, Snackbar.LENGTH_SHORT)
    }

}

@BindingAdapter("profileImage")
fun loadImage(view: ImageView, imageUrl: String?) {
    Glide.with(view.context)
        .load(imageUrl)
        .apply(RequestOptions().fitCenter())
        .into(view)
}

@BindingAdapter("app:author")
fun author(textView: TextView, value:String?) {
    if(value.equals("null") || value==null  || value.isNullOrEmpty()){
        textView.visibility = View.GONE
    }else {
        textView.visibility = View.VISIBLE
        textView.text = "Author : " + value.toString() + ""
    }
}

@BindingAdapter("app:date")
fun date(textView: TextView, value:String?) {
    if(value.equals("null") || value==null || value.isNullOrEmpty()){
        textView.visibility = View.GONE
    }else {
        textView.visibility = View.VISIBLE
        textView.text = "Date : " + value.toString().split(" ")[0] + ""
    }
}

@BindingAdapter("app:dateAndPublication")
fun dateAndPublication(textView: TextView, news: News?) {
    if(news?.published_date.isNullOrEmpty()){
        textView.visibility = View.GONE
    }else {
        textView.visibility = View.VISIBLE
        textView.text = "Date : " + news?.published_date.toString().split(" ")[0] + "   Pub : " + news?.clean_url.toString()
    }
}


@BindingAdapter("app:summary")
fun summary(textView: TextView, value:String?) {
    if(value.equals("null") || value==null || value.isNullOrEmpty()){
        textView.visibility = View.GONE
    }else {
        textView.visibility = View.VISIBLE
        textView.text = "Summary : \n\n\t\t\t\t\t\t\t" + value.toString() + ""
    }
}

@BindingAdapter("app:excerpt")
fun excerpt(textView: TextView, value:String?) {
    if(value.equals("null") || value==null || value.isNullOrEmpty()){
        textView.visibility = View.GONE
    }else {
        textView.visibility = View.VISIBLE
        textView.text = "Excerpt : \n\n\t\t\t\t\t\t\t" + value.toString() + ""
    }
}

@BindingAdapter("app:publication")
fun publication(textView: TextView, value:String?) {
    if(value.equals("null") || value==null || value.isNullOrEmpty()){
        textView.visibility = View.GONE
    }else {
        textView.visibility = View.VISIBLE
        textView.text = "Publication : " + value.toString() + ""
    }
}