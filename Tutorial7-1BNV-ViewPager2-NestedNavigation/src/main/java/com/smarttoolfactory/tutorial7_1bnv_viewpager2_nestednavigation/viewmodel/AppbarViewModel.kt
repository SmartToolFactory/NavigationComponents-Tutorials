package com.smarttoolfactory.tutorial7_1bnv_viewpager2_nestednavigation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController

class AppbarViewModel : ViewModel() {
    val currentNavController = MutableLiveData<NavController?>()
}