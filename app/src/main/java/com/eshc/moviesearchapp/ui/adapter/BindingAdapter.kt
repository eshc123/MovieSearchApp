package com.eshc.moviesearchapp.ui.adapter

import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.text.HtmlCompat
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

@BindingAdapter("imageUrl")
fun AppCompatImageView.setImageUrl(resource : String?){
    when(resource){
        null, "" -> return
        else -> {
            Glide.with(this.context)
                .load(resource)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .into(this)
        }
    }
}

@BindingAdapter("htmlText")
fun AppCompatTextView.setHtmlText(resource : String?){
    when(resource){
        null, "" -> return
        else -> {
            text = HtmlCompat.fromHtml(
                resource,
                HtmlCompat.FROM_HTML_MODE_COMPACT
            )
        }
    }
}