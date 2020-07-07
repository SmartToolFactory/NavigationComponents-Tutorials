package com.smarttoolfactory.tutorial8_2dynamicfeatures_complexarchitecture.api

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Post(
    val id: Int,
    val userId: Int,
    val title: String,
    val body: String
) : Parcelable