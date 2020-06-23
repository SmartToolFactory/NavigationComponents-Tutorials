package com.smarttoolfactory.tutorial6_4_navigationui_viewpager_fragmenttoolbar_nested_navigation.blankfragment

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.smarttoolfactory.tutorial6_4_navigationui_viewpager_fragmenttoolbar.R
import com.smarttoolfactory.tutorial6_4_navigationui_viewpager_fragmenttoolbar.databinding.FragmentLogin1Binding

/**
 * This fragment is added to main graph via [MainFragment]'s  [NavHostFragment]
 */
class LoginFragment1 : BaseDataBindingFragment<FragmentLogin1Binding>() {

    override fun getLayoutRes(): Int = R.layout.fragment_login1

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataBinding.buttonLogin.setOnClickListener {
            findNavController().navigate(R.id.action_main_dest_to_loginFragment2)
        }

    }
}

