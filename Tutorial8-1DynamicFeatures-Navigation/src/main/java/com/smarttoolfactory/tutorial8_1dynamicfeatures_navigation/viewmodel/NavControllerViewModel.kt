package com.smarttoolfactory.tutorial8_1dynamicfeatures_navigation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.smarttoolfactory.tutorial8_1dynamicfeatures_navigation.util.Event

class NavControllerViewModel : ViewModel() {
    val currentNavController = MutableLiveData<Event<NavController>>()
}