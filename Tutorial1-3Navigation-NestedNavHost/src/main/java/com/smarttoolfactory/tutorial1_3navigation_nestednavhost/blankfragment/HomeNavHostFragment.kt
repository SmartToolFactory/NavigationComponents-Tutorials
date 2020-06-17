package com.smarttoolfactory.tutorial1_3navigation_nestednavhost.blankfragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.smarttoolfactory.tutorial1_3navigation_nestednavhost.R
import com.smarttoolfactory.tutorial1_3navigation_nestednavhost.databinding.FragmentHomeNavhostBinding

class HomeNavHostFragment : BaseDataBindingFragment<FragmentHomeNavhostBinding>() {
    override fun getLayoutRes(): Int = R.layout.fragment_home_navhost

    private var navController: NavController? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val nestedNavHostFragment =
            childFragmentManager.findFragmentById(R.id.nested_nav_host_fragment) as? NavHostFragment
        navController = nestedNavHostFragment?.navController

        navController?.navigate(R.id.homeFragment1)

        listenBackStack()
    }

    private fun listenBackStack() {

        // Get NavHostFragment
        val navHostFragment =
            childFragmentManager.findFragmentById(R.id.nested_nav_host_fragment)

        // ChildFragmentManager of the current NavHostFragment
        val navHostChildFragmentManager = navHostFragment?.childFragmentManager

        navHostChildFragmentManager?.addOnBackStackChangedListener {

            val backStackEntryCount = navHostChildFragmentManager!!.backStackEntryCount
            val fragments = navHostChildFragmentManager!!.fragments

            Toast.makeText(
                requireContext(),
                "😛 ChildFragmentManager backStackEntryCount: $backStackEntryCount",
                Toast.LENGTH_SHORT
            ).show()
        }


        val callback = object : OnBackPressedCallback(true) {

            override fun handleOnBackPressed() {

                val backStackEntryCount = navHostChildFragmentManager!!.backStackEntryCount

                Toast.makeText(
                    requireContext(),
                    "🤨 HomeNavHost backStackEntryCount: $backStackEntryCount",
                    Toast.LENGTH_SHORT
                ).show()

                if (backStackEntryCount == 1) {
                    // We are the root of nested navigation, remove this callback
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