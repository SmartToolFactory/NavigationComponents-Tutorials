package com.smarttoolfactory.tutorial2_1navigationpassdata.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Data(
    val name: String?,
    val value: Int?
) : Parcelable