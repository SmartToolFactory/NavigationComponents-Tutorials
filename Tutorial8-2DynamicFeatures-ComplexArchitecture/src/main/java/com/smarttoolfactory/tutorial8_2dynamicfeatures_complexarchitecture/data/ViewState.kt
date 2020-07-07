package com.smarttoolfactory.tutorial8_2dynamicfeatures_complexarchitecture.data

import com.smarttoolfactory.tutorial8_2dynamicfeatures_complexarchitecture.api.Status


class ViewState<T>(
    val status: Status,
    val data: T? = null,
    val error: Throwable? = null
) {
    fun isLoading() = status == Status.LOADING

    fun getErrorMessage() = error?.message

    fun shouldShowErrorMessage() = error != null
}