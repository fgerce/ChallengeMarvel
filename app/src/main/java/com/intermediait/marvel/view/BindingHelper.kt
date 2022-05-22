package com.intermediait.marvel.view

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.intermediait.marvel.R
import com.intermediait.marvel.domain.models.ThumbnailURL

class BindingHelper {
    companion object {
        @BindingAdapter("imageUrl")
        @JvmStatic
        fun loadImage(imageView: ImageView, thumbnail: ThumbnailURL?){
            when(thumbnail){
                ThumbnailURL.Empty -> {
                    imageView.setImageResource(R.drawable.ic_fb_stormshadow)
                }
                is ThumbnailURL.URL -> {
                    Glide
                        .with(imageView.rootView)
                        .load(thumbnail.url.replaceFirst("http://", "https://"))
                        .placeholder(R.drawable.ic_fb_stormshadow)
                        .into(imageView)
                }
                else -> {
                    imageView.setImageResource(R.drawable.ic_fb_stormshadow)
                }
            }
        }
    }
}