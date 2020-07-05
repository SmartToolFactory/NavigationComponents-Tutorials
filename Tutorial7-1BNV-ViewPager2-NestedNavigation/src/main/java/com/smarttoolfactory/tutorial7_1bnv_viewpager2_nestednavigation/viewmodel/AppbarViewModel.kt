package com.smarttoolfactory.tutorial7_1bnv_viewpager2_nestednavigation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.smarttoolfactory.tutorial7_2bnv_viewpager2_complexarchitecture.util.Event

class AppbarViewModel : ViewModel() {
    val currentNavController = MutableLiveData<Event<NavController>>()
}