package com.smarttoolfactory.tutorial6_3navigationui_viewpager2_appbar.blankfragment

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.NavHostFragment
import com.smarttoolfactory.tutorial6_3navigationui_viewpager2_appbar.R
import com.smarttoolfactory.tutorial6_3navigationui_viewpager2_appbar.databinding.FragmentLogin1Binding

/**
 * This fragment is added to main graph via [MainFragment]'s  [NavHostFragment]
 */
class LoginFragment1 : BaseDataBindingFragment<FragmentLogin1Binding>() {

    override fun getLayoutRes(): Int = R.layout.fragment_login1

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataBinding.buttonLogin.setOnClickListener {
//            findNavController().navigate(R.id.action_main_dest_to_loginFragment2)
        }

    }
}

