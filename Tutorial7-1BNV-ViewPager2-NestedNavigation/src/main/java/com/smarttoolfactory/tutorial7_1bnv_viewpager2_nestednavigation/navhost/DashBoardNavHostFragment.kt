package com.smarttoolfactory.tutorial7_1bnv_viewpager2_nestednavigation.navhost

import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.smarttoolfactory.tutorial7_1bnv_viewpager2_nestednavigation.R
import com.smarttoolfactory.tutorial7_1bnv_viewpager2_nestednavigation.blankfragment.BaseDataBindingFragment
import com.smarttoolfactory.tutorial7_1bnv_viewpager2_nestednavigation.databinding.FragmentNavhostDashboardBinding
import com.smarttoolfactory.tutorial7_1bnv_viewpager2_nestednavigation.viewmodel.AppbarViewModel


class DashBoardNavHostFragment : BaseDataBindingFragment<FragmentNavhostDashboardBinding>() {
    override fun getLayoutRes(): Int = R.layout.fragment_navhost_dashboard

    private val appbarViewModel by activityViewModels<AppbarViewModel>()

    private var navController: NavController? = null

    private val nestedNavHostFragmentId = R.id.nested_nav_host_fragment_dashboard

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        println("🏂 ${this.javaClass.simpleName}#${this.hashCode()} onCreate()")

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        println("🏂 ${this.javaClass.simpleName}#${this.hashCode()}  onViewCreated()")

        val nestedNavHostFragment =
            childFragmentManager.findFragmentById(nestedNavHostFragmentId) as? NavHostFragment
        navController = nestedNavHostFragment?.navController

        // Listen on back press
        listenOnBackPressed()

    }


    private fun listenOnBackPressed() {
//        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
    }

    override fun onResume() {
        super.onResume()
        println("🏂 ${this.javaClass.simpleName} #${this.hashCode()}  onResume()")
//        callback.isEnabled = true

        // Set this navController as ViewModel's navController
        appbarViewModel.currentNavController.value = navController
    }

    override fun onPause() {
        super.onPause()
        println("🏂 ${this.javaClass.simpleName} #${this.hashCode()}  onPause()")
//        callback.isEnabled = false
    }

    override fun onDestroyView() {
        super.onDestroyView()
        println("🏂 ${this.javaClass.simpleName} #${this.hashCode()}  onDestroyView()")
    }

    override fun onDestroy() {
        super.onDestroy()
        println("🏂 ${this.javaClass.simpleName} #${this.hashCode()}  onDestroy()")
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
//            println("🏂 ${this@DashBoardNavHostFragment.javaClass.simpleName} #${this@DashBoardNavHostFragment.hashCode()} handleOnBackPressed()")
//
//            // Check if it's the root of nested fragments in this navhosts
//            if (navController?.currentDestination?.id == navController?.graph?.startDestination) {
//
//                Toast.makeText(requireContext(), "🏂 AT START DESTINATION ", Toast.LENGTH_SHORT)
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
//            } else if (isVisible) {
//                navController?.navigateUp()
//            }
//
//        }
//    }


}