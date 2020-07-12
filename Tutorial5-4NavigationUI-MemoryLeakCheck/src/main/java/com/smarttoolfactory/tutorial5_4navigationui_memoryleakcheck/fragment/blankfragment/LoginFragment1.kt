package com.smarttoolfactory.tutorial5_4navigationui_memoryleakcheck.fragment.blankfragment

import android.os.Bundle
import android.view.View
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.smarttoolfactory.tutorial5_4navigationui_memoryleakcheck.R
import com.smarttoolfactory.tutorial5_4navigationui_memoryleakcheck.databinding.FragmentLogin1Binding

class LoginFragment1 : BaseDataBindingFragment<FragmentLogin1Binding>() {

    override fun getLayoutRes(): Int = R.layout.fragment_login1


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        println("🔥 LoginFragment1 navController: ${findNavController()}")

        val binding = dataBinding!!

        binding.buttonLogin.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment1_to_loginFragment2)
        }


        binding.buttonLoginMain.setOnClickListener {
            requireActivity().findNavController(R.id.main_nav_host_fragment)
                .navigate(R.id.action_mainFragment_to_loginFragment2)
        }
    }


}

