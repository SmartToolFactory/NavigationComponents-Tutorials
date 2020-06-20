package com.smarttoolfactory.tutorial1_3navigation_nestednavhost.blankfragment

import android.os.Bundle
import android.view.View
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.smarttoolfactory.tutorial1_3navigation_nestednavhost.R
import com.smarttoolfactory.tutorial1_3navigation_nestednavhost.databinding.FragmentHome1Binding

class HomeFragment1 : BaseDataBindingFragment<FragmentHome1Binding>() {

    override fun getLayoutRes(): Int = R.layout.fragment_home1

    private var count = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // we can get the innermost NavController using this view,
        // because we are inside its subtree:
        val nestedNavController = Navigation.findNavController(view)

        // we can find the outer NavController passing the owning Activity
        // and the id of a view associated to that NavController,
        // for example the NavHostFragment id:
        val mainNavController =
            Navigation.findNavController(requireActivity(), R.id.main_nav_host_fragment)

        dataBinding.tvTitle.text = "${this.javaClass.simpleName} Count: $count"

        dataBinding.btnIncrease.setOnClickListener {
            dataBinding.tvTitle.text = "${this.javaClass.simpleName} Count: ${count++}"
        }

        dataBinding.btnNextPage.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment1_to_homeFragment2)
        }

    }

}
