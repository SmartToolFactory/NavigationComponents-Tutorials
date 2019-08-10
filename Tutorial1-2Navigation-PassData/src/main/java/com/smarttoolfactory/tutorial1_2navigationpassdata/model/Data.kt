package com.smarttoolfactory.tutorial1_2navigationpassdata.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Data(
    val name: String?,
    val value: Int?
) : Parcelable