package com.smarttoolfactory.tutorial6_3navigationui_viewpager2_appbar.navhost

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.smarttoolfactory.tutorial6_3navigationui_viewpager2_appbar.blankfragment.BaseDataBindingFragment
import com.smarttoolfactory.tutorial6_3navigationui_viewpager2_appbar.R
import com.smarttoolfactory.tutorial6_3navigationui_viewpager2_appbar.databinding.FragmentNavhostDashboardBinding


/**
 * Navigation host fragment for the Home tab.
 *
 * * [findNavController] returns the main navController, not the one required for navigating
 * nested [Fragment]s. That navController is retrieved from [NavHostFragment] of this fragment.
 *
 * * [DashBoardNavHostFragment] graph uses [DashBoardNavHostFragment] as start destination
 * so it should **navigate to next destination** to not get stuck.
 */
class DashBoardNavHostFragment : BaseDataBindingFragment<FragmentNavhostDashboardBinding>() {
    override fun getLayoutRes(): Int = R.layout.fragment_navhost_dashboard

    var navController: NavController? = null

    private val nestedNavHostFragmentId = R.id.nested_nav_host_fragment_dashboard
    private val navGraphId = R.navigation.nav_graph_dashboard

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        println("🏂  ${this.javaClass.simpleName} onCreateView() ${this.javaClass.simpleName} #${this.hashCode()}")
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val nestedNavHostFragment =
            childFragmentManager.findFragmentById(nestedNavHostFragmentId) as? NavHostFragment
        navController = nestedNavHostFragment?.navController


        // listen back stack changes for this NavHost
//        listenBackStack()

        // Listen on back press
        listenOnBackPressed()

    }

    private fun listenBackStack() {

        // Get NavHostFragment
        val navHostFragment =
            childFragmentManager.findFragmentById(nestedNavHostFragmentId)
        // ChildFragmentManager of the current NavHostFragment
        val navHostChildFragmentManager = navHostFragment?.childFragmentManager

        navHostChildFragmentManager?.addOnBackStackChangedListener {

            val backStackEntryCount = navHostChildFragmentManager.backStackEntryCount

            if (backStackEntryCount > 0)
                callback.isEnabled = true

        }

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

    override fun onDestroyView() {
        super.onDestroyView()
        println("🏂 ${this.javaClass.simpleName} onDestroyView()")
    }

    val callback = object : OnBackPressedCallback(true) {

        override fun handleOnBackPressed() {

            // Get NavHostFragment
            val navHostFragment =
                childFragmentManager.findFragmentById(nestedNavHostFragmentId)
            // ChildFragmentManager of the current NavHostFragment
            val navHostChildFragmentManager = navHostFragment?.childFragmentManager

            val currentDestination = navController?.currentDestination
            val backStackEntryCount = navHostChildFragmentManager!!.backStackEntryCount

            val isAtStartDestination =
                (navController?.currentDestination?.id == navController?.graph?.startDestination)

            // Returns only the fragment on top
            val fragments = navHostChildFragmentManager.fragments
            fragments.forEach {
                println(
                    "🏂 ${this.javaClass.simpleName} handleOnBackPressed() " +
                            "fragment: ${it.javaClass.simpleName} #${it.hashCode()}," +
                            "backStackEntryCount: $backStackEntryCount, " +
                            " isVisible: ${it.isVisible}, " +
                            ", isResumed: ${it.isResumed}, " +
                            ", isAtStartDestination: ${isAtStartDestination}, " +
                            "navController currentBackStackEntry: ${navController!!.currentBackStackEntry!!.destination}\n" +
                            "CURRENT DEST: ${currentDestination!!},  CURRENT DEST ID: ${currentDestination.id}, " +
                            "START DEST: ${navController!!.graph.startDestination}"
                )
            }

            // Check if it's the root of nested fragments in this navhosts
            if (navController?.currentDestination?.id == navController?.graph?.startDestination) {

                Toast.makeText(requireContext(), "🏂 AT START DESTINATION ", Toast.LENGTH_SHORT)
                    .show()

                remove()
                requireActivity().onBackPressed()
            } else if (isVisible) {
                navController?.navigateUp()
            }

            Toast.makeText(
                requireContext(),
                "🏂 ${this.javaClass.simpleName} backStackEntryCount: $backStackEntryCount\n" +
                        "isAtStartDestination:  $isAtStartDestination, \n" +
                        "isVisible:  $isVisible, \n" +
                        "CURRENT DEST:  ${currentDestination!!.id}, " +
                        "START DEST: ${navController!!.graph.startDestination}",
                Toast.LENGTH_SHORT
            ).show()

        }
    }

}