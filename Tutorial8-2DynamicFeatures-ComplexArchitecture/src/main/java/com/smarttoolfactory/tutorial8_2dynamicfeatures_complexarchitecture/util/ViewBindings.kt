package com.smarttoolfactory.tutorial8_2dynamicfeatures_complexarchitecture.util

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.smarttoolfactory.tutorial8_2dynamicfeatures_complexarchitecture.R
import com.smarttoolfactory.tutorial8_2dynamicfeatures_complexarchitecture.api.Post


/*
    *** Bindings for RecyclerView ***
 */

/**
 * [BindingAdapter]s for the [Post]s to ListAdapter.
 */
@BindingAdapter("app:items")
fun RecyclerView.setItems(items: List<Post>?) {

    items?.let {
        (adapter as ListAdapter<Post, *>)?.submitList(items)

    }
}


/**
 * Binding adapter used with this class android:src used with binding of this object
 * loads image from url into specified view
 *
 * @param view image to be loaded into
 * @param path of the image to be fetched
 */
@BindingAdapter("imageSrc")
fun setImageUrl(view: ImageView, userId: Int) {

    try {

        val drawableRes = when {
            userId % 6 == 0 -> {
                R.drawable.avatar_1_raster
            }
            userId % 6 == 1 -> {
                R.drawable.avatar_2_raster
            }
            userId % 6 == 2 -> {
                R.drawable.avatar_3_raster
            }
            userId % 6 == 3 -> {
                R.drawable.avatar_4_raster
            }
            userId % 6 == 4 -> {
                R.drawable.avatar_5_raster
            }
            else -> {
                R.drawable.avatar_6_raster
            }
        }

        val requestOptions = RequestOptions()
        requestOptions.placeholder(R.drawable.ic_launcher_background)

        Glide
            .with(view.context)
            .setDefaultRequestOptions(requestOptions)
            .load(drawableRes)
            .into(view)

    } catch (e: Exception) {
        e.printStackTrace()
    }
}

/**
 * Display or hide a view based on a condition
 */
@BindingAdapter("visibilityBasedOn")
fun View.visibilityBasedOn(condition: Boolean) {
    visibility = if (condition) View.VISIBLE else View.GONE

}


