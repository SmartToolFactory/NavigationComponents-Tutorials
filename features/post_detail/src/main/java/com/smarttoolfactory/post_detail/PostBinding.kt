package com.smarttoolfactory.post_detail

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.smarttoolfactory.tutorial8_2dynamicfeatures_complexarchitecture.R

// TODO Same binding in app module not working in this feature module, why?
@BindingAdapter("imageSource")
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
