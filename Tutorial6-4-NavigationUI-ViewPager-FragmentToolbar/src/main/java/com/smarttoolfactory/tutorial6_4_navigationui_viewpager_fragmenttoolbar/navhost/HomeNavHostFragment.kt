package com.smarttoolfactory.tutorial6_4_navigationui_viewpager_fragmenttoolbar.navhost

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.smarttoolfactory.tutorial6_4_navigationui_viewpager_fragmenttoolbar.R
import com.smarttoolfactory.tutorial6_4_navigationui_viewpager_fragmenttoolbar.blankfragment.BaseDataBindingFragment
import com.smarttoolfactory.tutorial6_4_navigationui_viewpager_fragmenttoolbar.databinding.FragmentNavhostHomeBinding


class HomeNavHostFragment : BaseDataBindingFragment<FragmentNavhostHomeBinding>() {
    override fun getLayoutRes(): Int = R.layout.fragment_navhost_home

    init {
        println("🏠 HOME INIT")
    }

    private var navController: NavController? = null

    private val nestedNavHostFragmentId = R.id.nested_nav_host_fragment_home

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val nestedNavHostFragment =
            childFragmentManager.findFragmentById(nestedNavHostFragmentId) as? NavHostFragment
        navController = nestedNavHostFragment?.navController

        // Listen on back press
        listenOnBackPressed()

    }

    private fun listenOnBackPressed() {
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
    }

    override fun onResume() {
        super.onResume()
        callback.isEnabled = true
    }

    override fun onPause() {
        super.onPause()
        callback.isEnabled = false
    }

    val callback = object : OnBackPressedCallback(true) {

        override fun handleOnBackPressed() {

            // Check if it's the root of nested fragments in this navhost
            if (navController?.currentDestination?.id == navController?.graph?.startDestination) {

                Toast.makeText(requireContext(), "🏠 AT START DESTINATION ", Toast.LENGTH_SHORT)
                    .show()

                /*
                    Disable this callback because calls OnBackPressedDispatcher
                     gets invoked  calls this callback  gets stuck in a loop
                 */
                isEnabled = false
                requireActivity().onBackPressed()
                isEnabled = true

            } else {
                navController?.navigateUp()
            }

        }
    }

}