package com.smarttoolfactory.tutorial8_2dynamicfeatures_complexarchitecture.fragment.navhost

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.smarttoolfactory.tutorial8_2dynamicfeatures_complexarchitecture.R
import com.smarttoolfactory.tutorial8_2dynamicfeatures_complexarchitecture.fragment.blankfragment.BaseDataBindingFragment
import com.smarttoolfactory.tutorial8_2dynamicfeatures_complexarchitecture.databinding.FragmentNavhostPostVerticalBinding
import com.smarttoolfactory.tutorial8_2dynamicfeatures_complexarchitecture.util.Event
import com.smarttoolfactory.tutorial8_2dynamicfeatures_complexarchitecture.viewmodel.NavControllerViewModel

class PostVerticalNavHostFragment : BaseDataBindingFragment<FragmentNavhostPostVerticalBinding>() {
    override fun getLayoutRes(): Int = R.layout.fragment_navhost_post_vertical

    private val navControllerViewModel by activityViewModels<NavControllerViewModel>()

    private var navController: NavController? = null

    private val nestedNavHostFragmentId = R.id.nested_nav_host_fragment_post_vertical


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val nestedNavHostFragment =
            childFragmentManager.findFragmentById(nestedNavHostFragmentId) as? NavHostFragment
        navController = nestedNavHostFragment?.navController

    }

    override fun onResume() {
        super.onResume()

        // Set this navController as ViewModel's navController
        navController?.let {
            navControllerViewModel.currentNavController.value = Event(it)
        }
    }
}