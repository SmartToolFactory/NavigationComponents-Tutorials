package com.smarttoolfactory.tutorial1_3navigation_nestednavhost.blankfragment

import android.os.Bundle
import android.view.View
import androidx.navigation.Navigation
import com.smarttoolfactory.tutorial1_3navigation_nestednavhost.R
import com.smarttoolfactory.tutorial1_3navigation_nestednavhost.databinding.FragmentHome3Binding

class HomeFragment3 : BaseDataBindingFragment<FragmentHome3Binding>() {
    override fun getLayoutRes(): Int = R.layout.fragment_home3

    private var count = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dataBinding.tvTitle.text = "${this.javaClass.simpleName} Count: $count"

        dataBinding.btnIncrease.setOnClickListener {
            dataBinding.tvTitle.text = "${this.javaClass.simpleName} Count: ${count++}"
        }

        val mainNavController =
            Navigation.findNavController(requireActivity(), R.id.main_nav_host_fragment)

        dataBinding.btnGoToStart.setOnClickListener {

            // 🔥Using destination belong to main_nav_host with nested navHost causes app to crash
//            findNavController().navigate(R.id.action_global_start)

            mainNavController.navigate(R.id.action_global_start)/**/
        }

    }

}
