package com.smarttoolfactory.tutorial7_3bnv_viewpager2_fragmenttoolbar_mixednavigation.fragment.blankfragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.smarttoolfactory.tutorial7_3bnv_viewpager2_fragmenttoolbar_mixednavigation.R
import com.smarttoolfactory.tutorial7_3bnv_viewpager2_fragmenttoolbar_mixednavigation.databinding.FragmentLogin1Binding
import com.smarttoolfactory.tutorial7_3bnv_viewpager2_fragmenttoolbar_mixednavigation.fragment.viewpagerfragment.ViewPagerContainerFragment
import com.smarttoolfactory.tutorial7_3bnv_viewpager2_fragmenttoolbar_mixednavigation.util.Event
import com.smarttoolfactory.tutorial7_3bnv_viewpager2_fragmenttoolbar_mixednavigation.viewmodel.NavControllerViewModel

/**
 * This fragment is added to graph via [ViewPagerContainerFragment]'s  [NavHostFragment]
 */
class LoginFragment1 : BaseDataBindingFragment<FragmentLogin1Binding>() {

    override fun getLayoutRes(): Int = R.layout.fragment_login1

    private val navControllerViewModel by activityViewModels<NavControllerViewModel>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dataBinding!!.buttonLogin.setOnClickListener {
            findNavController().navigate(R.id.action_view_pager_dest_to_loginFragment2)
        }

    }

    override fun onResume() {
        super.onResume()
        // Set this navController as ViewModel's navController
        navControllerViewModel.currentNavController.value = Event(findNavController())
    }

}

