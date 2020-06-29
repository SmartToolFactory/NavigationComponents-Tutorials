package com.smarttoolfactory.tutorial7_2bnv_viewpager2_complexarchitecture.navhost

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.smarttoolfactory.tutorial7_2bnv_viewpager2_complexarchitecture.R
import com.smarttoolfactory.tutorial7_2bnv_viewpager2_complexarchitecture.blankfragment.BaseDataBindingFragment
import com.smarttoolfactory.tutorial7_2bnv_viewpager2_complexarchitecture.databinding.FragmentNavhostHomeBinding
import com.smarttoolfactory.tutorial7_2bnv_viewpager2_complexarchitecture.viewmodel.AppbarViewModel


class HomeNavHostFragment : BaseDataBindingFragment<FragmentNavhostHomeBinding>() {
    override fun getLayoutRes(): Int = R.layout.fragment_navhost_home

    private val appbarViewModel by activityViewModels<AppbarViewModel>()

    private var navController: NavController? = null

    private val nestedNavHostFragmentId = R.id.nested_nav_host_fragment_home


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val nestedNavHostFragment =
            childFragmentManager.findFragmentById(nestedNavHostFragmentId) as? NavHostFragment
        navController = nestedNavHostFragment?.navController

    }

    override fun onResume() {
        super.onResume()
        // Set this navController as ViewModel's navController
        appbarViewModel.currentNavController.value = navController
    }

}