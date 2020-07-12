package com.smarttoolfactory.tutorial8_2dynamicfeatures_complexarchitecture.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.smarttoolfactory.tutorial8_2dynamicfeatures_complexarchitecture.util.Event

class NavControllerViewModel : ViewModel() {
    val currentNavController = MutableLiveData<Event<NavController?>>()
}