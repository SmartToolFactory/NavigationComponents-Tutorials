package com.smarttoolfactory.tutorial6_5navigationui_viewpager2_fragmenttoolbar_mixednavigation.navhost

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.smarttoolfactory.tutorial6_5navigationui_viewpager2_fragmenttoolbar_mixednavigation.R
import com.smarttoolfactory.tutorial6_5navigationui_viewpager2_fragmenttoolbar_mixednavigation.databinding.FragmentNavhostHomeBinding
import com.smarttoolfactory.tutorial6_5navigationui_viewpager2_fragmenttoolbar_mixednavigation.blankfragment.BaseDataBindingFragment


/**
 * Using [FragmentStateAdapter.registerFragmentTransactionCallback] with [FragmentStateAdapter] solves back navigation instead of using [OnBackPressedCallback.handleOnBackPressed] in every [NavHostFragment]
 * ### Should set app:defaultNavHost="true" for [NavHostFragment] for this to work
 */
class HomeNavHostFragment : BaseDataBindingFragment<FragmentNavhostHomeBinding>() {
    override fun getLayoutRes(): Int = R.layout.fragment_navhost_home

    private var navController: NavController? = null

    private val nestedNavHostFragmentId = R.id.nested_nav_host_fragment_home

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val nestedNavHostFragment =
            childFragmentManager.findFragmentById(nestedNavHostFragmentId) as? NavHostFragment
        navController = nestedNavHostFragment?.navController

        val appBarConfig = AppBarConfiguration(navController!!.graph)
        dataBinding!!.toolbar.setupWithNavController(navController!!, appBarConfig)

        // Listen on back press
//        listenOnBackPressed()

    }

    private fun listenOnBackPressed() {
//        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
    }


    override fun onResume() {
        super.onResume()
//        callback.isEnabled = true
    }

    override fun onPause() {
        super.onPause()
//        callback.isEnabled = false
    }

    /**
     * This callback should be created with Disabled because on rotation ViewPager creates
     * NavHost fragments that are not on screen, destroys them afterwards but it might take
     * up to 5 seconds.
     *
     * ### Note: During that interval touching back button sometimes call incorrect [OnBackPressedCallback.handleOnBackPressed] instead of this one if callback is **ENABLED**
     */
//    val callback = object : OnBackPressedCallback(false) {
//
//        override fun handleOnBackPressed() {
//
//            // Check if it's the root of nested fragments in this navhost
//            if (navController?.currentDestination?.id == navController?.graph?.startDestination) {
//
//                Toast.makeText(requireContext(), "🏠 AT START DESTINATION ", Toast.LENGTH_SHORT)
//                    .show()
//
//                /*
//                    Disable this callback because calls OnBackPressedDispatcher
//                     gets invoked  calls this callback  gets stuck in a loop
//                 */
//                isEnabled = false
//                requireActivity().onBackPressed()
//                isEnabled = true
//
//            } else {
//                navController?.navigateUp()
//            }
//
//        }
//    }

}