package com.smarttoolfactory.tutorial7_3bnv_viewpager2_fragmenttoolbar_mixednavigation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.smarttoolfactory.tutorial7_3bnv_viewpager2_fragmenttoolbar_mixednavigation.util.Event

class NavControllerViewModel : ViewModel() {
    val currentNavController = MutableLiveData<Event<NavController>>()
}