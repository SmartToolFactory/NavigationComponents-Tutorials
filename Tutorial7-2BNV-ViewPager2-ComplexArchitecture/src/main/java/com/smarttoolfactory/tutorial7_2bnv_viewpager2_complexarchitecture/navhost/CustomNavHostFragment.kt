package com.smarttoolfactory.tutorial7_2bnv_viewpager2_complexarchitecture.navhost

import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.annotation.NavigationRes
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.NavHostFragment
import com.smarttoolfactory.tutorial7_2bnv_viewpager2_complexarchitecture.viewmodel.AppbarViewModel


class CustomNavHostFragment private constructor() : NavHostFragment() {

    private val appbarViewModel by activityViewModels<AppbarViewModel>()

    companion object {
        fun create(
            @NavigationRes navGraphId: Int,
            startDestinationArgs: Bundle? = null
        ): NavHostFragment {
            return NavHostFragment.create(navGraphId, startDestinationArgs)
        }
    }

    override fun onResume() {
        super.onResume()
        // Set this navController as ViewModel's navController
        appbarViewModel.currentNavController.value = navController
    }


}