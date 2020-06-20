package com.smarttoolfactory.tutorial6_2navigationui_viewpager2_nestednavhost.navhost

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.smarttoolfactory.tutorial6_2naigationui_viewpager2_nestednavhost.R
import com.smarttoolfactory.tutorial6_2naigationui_viewpager2_nestednavhost.databinding.FragmentNavhostDashboardBinding
import com.smarttoolfactory.tutorial6_2navigationui_viewpager2_nestednavhost.blankfragment.BaseDataBindingFragment

class DashBoardNavHostFragment : BaseDataBindingFragment<FragmentNavhostDashboardBinding>() {
    override fun getLayoutRes(): Int = R.layout.fragment_navhost_dashboard

    private var navController: NavController? = null

    private var startDestination = -1

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val nestedNavHostFragment =
            childFragmentManager.findFragmentById(R.id.nested_nav_host_fragment_dashboard) as? NavHostFragment
        navController = nestedNavHostFragment?.navController

        // 🔥 Alternative 1
//        navController?.navigate(R.id.homeFragment1)

        // 🔥 Alternative 2
        val navInflater = navController!!.navInflater
        val graph = navInflater.inflate(R.navigation.nav_graph_dashboard)
        nestedNavHostFragment!!.navController.graph = graph
        startDestination = graph.startDestination


        // Listen on back press
        listenOnBackPressed()

        // listen back stack changes for this NavHost
        listenBackStack()

    }

    private fun listenBackStack() {

        // Get NavHostFragment
        val navHostFragment =
            childFragmentManager.findFragmentById(R.id.nested_nav_host_fragment_dashboard)

        // ChildFragmentManager of the current NavHostFragment
        val navHostChildFragmentManager = navHostFragment?.childFragmentManager

        navHostChildFragmentManager?.addOnBackStackChangedListener {

            val backStackEntryCount = navHostChildFragmentManager.backStackEntryCount
            val fragments = navHostChildFragmentManager.fragments

            fragments.forEach {
                println(" ⏱ DashBoardNavHost addOnBackStackChangedListener() fragment: ${it.javaClass.simpleName}, isVisible: ${it.isVisible}, isResumed: ${it.isResumed}")
            }

            val currentFragment =
                navHostChildFragmentManager.findFragmentById(R.id.nested_nav_host_fragment_dashboard)

            Toast.makeText(
                requireContext(),
                "🥶 DashBoardNavHostFragment ChildFragmentManager backStackEntryCount: $backStackEntryCount, current fragment: ${currentFragment?.javaClass?.simpleName}",
                Toast.LENGTH_SHORT
            ).show()
        }

    }

    private fun listenOnBackPressed() {

        // Get NavHostFragment
        val navHostFragment =
            childFragmentManager.findFragmentById(R.id.nested_nav_host_fragment_dashboard)

        // ChildFragmentManager of the current NavHostFragment
        val navHostChildFragmentManager = navHostFragment?.childFragmentManager


        val callback = object : OnBackPressedCallback(true) {

            override fun handleOnBackPressed() {

                val fragments = navHostChildFragmentManager!!.fragments
                val backStackEntryCount = navHostChildFragmentManager.backStackEntryCount

                Toast.makeText(
                    requireContext(),
                    "💀  DashBoardNavHostFragment backStackEntryCount: $backStackEntryCount",
                    Toast.LENGTH_SHORT
                ).show()

                val currentDestination = navController?.currentDestination
                fragments.forEach {
                    println(" ⏱ DashBoardNavHost handleOnBackPressed() fragment: ${it.javaClass.simpleName}, " +
                            "isVisible: ${it.isVisible}, " +
                            "isResumed: ${it.isResumed}, " +
                            "currentDestination: ${currentDestination!!.id}, " +
                            "startDestination: $startDestination")
                }

//                if (backStackEntryCount == 1) {
//                    // We are at the root of nested navigation, remove this callback
//                    remove()
//                    requireActivity().onBackPressed()
//                } else {
//                    navController?.navigateUp()
//                }
//                navController?.navigateUp()
            }
        }

//        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
    }
}