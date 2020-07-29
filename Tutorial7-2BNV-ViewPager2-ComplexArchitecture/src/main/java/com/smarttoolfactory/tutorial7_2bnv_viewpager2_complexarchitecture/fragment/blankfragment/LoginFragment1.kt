package com.smarttoolfactory.tutorial7_2bnv_viewpager2_complexarchitecture.fragment.blankfragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.smarttoolfactory.tutorial7_2bnv_viewpager2_complexarchitecture.R
import com.smarttoolfactory.tutorial7_2bnv_viewpager2_complexarchitecture.databinding.FragmentLogin1Binding
import com.smarttoolfactory.tutorial7_2bnv_viewpager2_complexarchitecture.util.Event
import com.smarttoolfactory.tutorial7_2bnv_viewpager2_complexarchitecture.viewmodel.AppbarViewModel
import com.smarttoolfactory.tutorial7_2bnv_viewpager2_complexarchitecture.fragment.viewpagerfragment.ViewPagerContainerFragment

/**
 * This fragment is added to main graph via [ViewPagerContainerFragment]'s  [NavHostFragment]
 */
class LoginFragment1 : BaseDataBindingFragment<FragmentLogin1Binding>() {

    override fun getLayoutRes(): Int = R.layout.fragment_login1

    private val appbarViewModel by activityViewModels<AppbarViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataBinding!!.buttonLogin.setOnClickListener {
            findNavController().navigate(R.id.action_main_dest_to_loginFragment2)
        }

    }

    override fun onResume() {
        super.onResume()
        // Set this navController as ViewModel's navController
        appbarViewModel.currentNavController.value = Event(findNavController())
    }

}
