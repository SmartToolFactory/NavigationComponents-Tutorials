package com.smarttoolfactory.tutorial7_2bnv_viewpager2_complexarchitecture.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController

class AppbarViewModel : ViewModel() {
    val currentNavController = MutableLiveData<NavController?>()
}