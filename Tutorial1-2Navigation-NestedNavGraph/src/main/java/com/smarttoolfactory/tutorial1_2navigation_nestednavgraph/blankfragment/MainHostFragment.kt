package com.smarttoolfactory.tutorial1_2navigation_nestednavgraph.blankfragment


import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.smarttoolfactory.tutorial1_2navigation_nestednavgraph.R
import com.smarttoolfactory.tutorial1_2navigation_nestednavgraph.databinding.FragmentMainHostBinding


class MainHostFragment : BaseDataBindingFragment<FragmentMainHostBinding>() {

    override fun getLayoutRes(): Int =
        R.layout.fragment_main_host

     lateinit var navController: NavController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val nestedNavHostFragment =
            childFragmentManager.findFragmentById(R.id.nested_nav_host_fragment) as? NavHostFragment
        navController = nestedNavHostFragment?.navController!!

        navController.navigatorProvider

    }

}
