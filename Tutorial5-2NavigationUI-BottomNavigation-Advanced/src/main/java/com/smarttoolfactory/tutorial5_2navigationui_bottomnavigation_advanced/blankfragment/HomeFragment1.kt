package com.smarttoolfactory.tutorial5_2navigationui_bottomnavigation_advanced.blankfragment

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.smarttoolfactory.tutorial5_2navigationui_bottomnavigation_advanced.R
import com.smarttoolfactory.tutorial5_2navigationui_bottomnavigation_advanced.databinding.FragmentHome1Binding

class HomeFragment1 : BaseDataBindingFragment<FragmentHome1Binding>() {

    override fun getLayoutRes(): Int = R.layout.fragment_home1

    var count = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dataBinding.btnIncrease.setOnClickListener {
            dataBinding.tvTitle.text = "Count: ${count++}"
        }

        dataBinding.btnNextPage.setOnClickListener {
//            findNavController().navigate(R.id.action_homeFragment1_to_homeFragment2)
        }

        println("🔥 HomeFragment1 navController: ${findNavController()}")

    }

}
