package com.smarttoolfactory.tutorial6_7navigationui_memoryleakcheck.blankfragment

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.smarttoolfactory.tutorial6_7navigationui_memoryleakcheck.R

/**
 * This fragment is added to main graph via [MainFragment]'s  [NavHostFragment]
 */
class LoginFragment1 :BaseFragment() {

    override fun getLayoutRes(): Int = R.layout.fragment_login1

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<Button>(R.id.buttonLogin).setOnClickListener {
            findNavController().navigate(R.id.action_main_dest_to_loginFragment2)
        }

    }
}

