package com.smarttoolfactory.tutorial7_2bnv_viewpager2_complexarchitecture.blankfragment

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.smarttoolfactory.tutorial7_2bnv_viewpager2_complexarchitecture.R
import com.smarttoolfactory.tutorial7_2bnv_viewpager2_complexarchitecture.databinding.FragmentLogin1Binding

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

