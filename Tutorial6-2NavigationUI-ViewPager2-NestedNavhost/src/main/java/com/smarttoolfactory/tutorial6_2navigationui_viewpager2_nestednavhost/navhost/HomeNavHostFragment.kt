package com.smarttoolfactory.tutorial6_2navigationui_viewpager2_nestednavhost.navhost

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.smarttoolfactory.tutorial6_2naigationui_viewpager2_nestednavhost.R
import com.smarttoolfactory.tutorial6_2naigationui_viewpager2_nestednavhost.databinding.FragmentNavhostHomeBinding
import com.smarttoolfactory.tutorial6_2navigationui_viewpager2_nestednavhost.blankfragment.BaseDataBindingFragment

class HomeNavHostFragment : BaseDataBindingFragment<FragmentNavhostHomeBinding>() {
    override fun getLayoutRes(): Int = R.layout.fragment_navhost_home

    private var navController: NavController? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val nestedNavHostFragment =
            childFragmentManager.findFragmentById(R.id.nested_nav_host_fragment_home) as? NavHostFragment
        navController = nestedNavHostFragment?.navController

        // 🔥 Alternative 1
        navController?.navigate(R.id.homeFragment1)

        // 🔥 Alternative 2
//        val navInflater = navController!!.navInflater
//        val graph = navInflater.inflate(R.navigation.nav_graph_home)
//        nestedNavHostFragment!!.navController.graph = graph


        // Listen on back press
        listenOnBackPressed()

        // listen back stack changes for this NavHost
        listenBackStack()


    }

    private fun listenBackStack() {

        // Get NavHostFragment
        val navHostFragment =
            childFragmentManager.findFragmentById(R.id.nested_nav_host_fragment_home)

        // ChildFragmentManager of the current NavHostFragment
        val navHostChildFragmentManager = navHostFragment?.childFragmentManager

        navHostChildFragmentManager?.addOnBackStackChangedListener {

            val backStackEntryCount = navHostChildFragmentManager!!.backStackEntryCount
            val fragments = navHostChildFragmentManager!!.fragments

            Toast.makeText(
                requireContext(),
                "😍 HomeNavHostFragment ChildFragmentManager backStackEntryCount: $backStackEntryCount",
                Toast.LENGTH_SHORT
            ).show()
        }

    }

    private fun listenOnBackPressed() {

        // Get NavHostFragment
        val navHostFragment =
            childFragmentManager.findFragmentById(R.id.nested_nav_host_fragment_home)

        // ChildFragmentManager of the current NavHostFragment
        val navHostChildFragmentManager = navHostFragment?.childFragmentManager


        val callback = object : OnBackPressedCallback(true) {

            override fun handleOnBackPressed() {

                val backStackEntryCount = navHostChildFragmentManager!!.backStackEntryCount

//                Toast.makeText(
//                    requireContext(),
//                    "🤨 HomeNavHost backStackEntryCount: $backStackEntryCount",
//                    Toast.LENGTH_SHORT
//                ).show()

                if (backStackEntryCount == 1) {
                    // We are at the root of nested navigation, remove this callback
                    remove()
                    requireActivity().onBackPressed()
                } else {
                    navController?.navigateUp()
                }
            }
        }

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
    }
}