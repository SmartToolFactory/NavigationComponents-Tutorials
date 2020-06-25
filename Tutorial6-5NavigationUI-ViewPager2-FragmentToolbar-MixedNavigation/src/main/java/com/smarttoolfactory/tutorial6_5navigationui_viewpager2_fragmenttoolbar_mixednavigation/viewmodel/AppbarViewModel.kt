package com.smarttoolfactory.tutorial6_5navigationui_viewpager2_fragmenttoolbar_mixednavigation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController

class AppbarViewModel : ViewModel() {

    val appbarParentVisibility= MutableLiveData<Boolean>().apply {
        this.value = false
    }


}