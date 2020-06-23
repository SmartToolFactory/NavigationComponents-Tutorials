package com.smarttoolfactory.tutorial6_3navigationui_viewpager2_appbar_nested_navigation.navhost

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.smarttoolfactory.tutorial6_3navigationui_viewpager2_appbar.R
import com.smarttoolfactory.tutorial6_3navigationui_viewpager2_appbar.databinding.FragmentNavhostDashboardBinding
import com.smarttoolfactory.tutorial6_3navigationui_viewpager2_appbar_nested_navigation.blankfragment.BaseDataBindingFragment

class DashBoardNavHostFragment : BaseDataBindingFragment<FragmentNavhostDashboardBinding>() {
    override fun getLayoutRes(): Int = R.layout.fragment_navhost_dashboard

    var dashboardNavController = MutableLiveData<NavController>()

    private var navController: NavController? = null

    private val nestedNavHostFragmentId = R.id.nested_nav_host_fragment_dashboard


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val nestedNavHostFragment =
            childFragmentManager.findFragmentById(nestedNavHostFragmentId) as? NavHostFragment
        navController = nestedNavHostFragment?.navController

        dashboardNavController.value = navController

        // Listen on back press
        listenOnBackPressed()

    }


    private fun listenOnBackPressed() {
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
    }

    override fun onResume() {
        super.onResume()
        println("🏂 ${this.javaClass.simpleName} onResume()")
        callback.isEnabled = true
    }

    override fun onPause() {
        super.onPause()
        println("🏂 ${this.javaClass.simpleName} onPause()")
        callback.isEnabled = false
    }

    val callback = object : OnBackPressedCallback(true) {

        override fun handleOnBackPressed() {

            // Check if it's the root of nested fragments in this navhosts
            if (navController?.currentDestination?.id == navController?.graph?.startDestination) {

                Toast.makeText(requireContext(), "🏂 AT START DESTINATION ", Toast.LENGTH_SHORT)
                    .show()

                /*
                    Disable this callback because calls OnBackPressedDispatcher
                     gets invoked  calls this callback  gets stuck in a loop
                 */
                isEnabled = false
                requireActivity().onBackPressed()
                isEnabled = true

            } else if (isVisible) {
                navController?.navigateUp()
            }

        }
    }

}