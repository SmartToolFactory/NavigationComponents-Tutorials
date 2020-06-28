package com.smarttoolfactory.tutorial6_2navigationui_viewpager2_nestednavhost.navhost

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.smarttoolfactory.tutorial6_2naigationui_viewpager2_nestednavhost.R
import com.smarttoolfactory.tutorial6_2naigationui_viewpager2_nestednavhost.databinding.FragmentNavhostHomeBinding
import com.smarttoolfactory.tutorial6_2navigationui_viewpager2_nestednavhost.blankfragment.BaseDataBindingFragment


class NotificationHostFragment : BaseDataBindingFragment<FragmentNavhostHomeBinding>() {
    override fun getLayoutRes(): Int = R.layout.fragment_navhost_notification

    var navController: NavController? = null

    private val nestedNavHostFragmentId = R.id.nested_nav_host_fragment_notification
    private val navGraphId = R.navigation.nav_graph_notification


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val nestedNavHostFragment =
            childFragmentManager.findFragmentById(nestedNavHostFragmentId) as? NavHostFragment
        navController = nestedNavHostFragment?.navController

        println(
            "⏰ ${this.javaClass.simpleName} onViewCreated(): ${this.javaClass.simpleName} #${this.hashCode()}\n" +
                    "navController currentBackStackEntry: ${navController!!.currentBackStackEntry!!.destination}\n" +
                    "currentDestination: ${navController!!.currentDestination}\n" +
                    "current dest id: ${navController!!.currentDestination!!.id}, " +
                    "startDestination: ${navController!!.graph.startDestination}, " +
                    "graph start dest: ${navController!!.navInflater.inflate(navGraphId).startDestination}"
        )

        /*
            🔥 Alternative 1
            Navigate to HomeFragment1 if there is no current destination and current destination
            is start destination. Set start destination as this fragment so it needs to
            navigate next destination.

            If start destination is NavHostFragment it's required to navigate to first
         */
//        if (navController!!.currentDestination == null || navController!!.currentDestination!!.id == navController!!.graph.startDestination) {
//            navController?.navigate(R.id.homeFragment1)
//        }

        /*
            🔥 Alternative 2 Reset graph to default status every time this fragment's view is created
            ❌ This does not work if initial destination if this fragment because it repeats
            creating this fragment in an infinite loop since graph is created every time
         */
//        val navInflater = navController!!.navInflater
//        nestedNavHostFragment!!.navController.graph = graph
//        val graph = navController!!.navInflater.inflate(navGraphId)
//        nestedNavHostFragment!!.navController.graph = graph

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
            val fragments = navHostChildFragmentManager.fragments
            val currentDestination = navController?.currentDestination

            fragments.forEach {

                println(
                    " ⏰ ${this.javaClass.simpleName} handleOnBackPressed() " +
                            "fragment: ${it.javaClass.simpleName} #${it.hashCode()}," +
                            "backStackEntryCount: $backStackEntryCount, " +
                            " isVisible: ${it.isVisible}, " +
                            " isVisible: ${it.isVisible}, " +
                            ", isResumed: ${it.isResumed}, " +
                            "currentDestination: ${currentDestination!!}, DEST ID: ${currentDestination.id}, " +
                            "startDestination: ${navController!!.graph.startDestination}"
                )
            }


            Toast.makeText(
                requireContext(),
                "⏰ ${this.javaClass.simpleName} ChildFragmentManager backStackEntryCount: $backStackEntryCount",
                Toast.LENGTH_SHORT
            ).show()
        }

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

    /**
     * This callback should be created with Disabled because on rotation ViewPager creates
     * NavHost fragments that are not on screen, destroys them afterwards but it might take
     * up to 5 seconds.
     *
     * ### Note: During that interval touching back button sometimes call incorrect [OnBackPressedCallback.handleOnBackPressed] instead of this one if callback is **ENABLED**
     */
    val callback = object : OnBackPressedCallback(false) {

        override fun handleOnBackPressed() {

            println("⏰ ${this@NotificationHostFragment.javaClass.simpleName} #${this@NotificationHostFragment.hashCode()} handleOnBackPressed()")

            // Get NavHostFragment
            val navHostFragment =
                childFragmentManager.findFragmentById(nestedNavHostFragmentId)
            // ChildFragmentManager of the current NavHostFragment
            val navHostChildFragmentManager = navHostFragment?.childFragmentManager

            val currentDestination = navController?.currentDestination
            val backStackEntryCount = navHostChildFragmentManager!!.backStackEntryCount

            val isAtStartDestination =
                (navController?.currentDestination?.id == navController?.graph?.startDestination)

            // 🔥 Returns only the fragment on top, fragmentManager uses REPLACE
            val fragments = navHostChildFragmentManager.fragments
            fragments.forEach {
//                println(
//                    "⏰ ${this.javaClass.simpleName} handleOnBackPressed() " +
//                            "fragment: ${it.javaClass.simpleName} #${it.hashCode()}," +
//                            "backStackEntryCount: $backStackEntryCount, " +
//                            " isVisible: ${it.isVisible}, " +
//                            ", isResumed: ${it.isResumed}, " +
//                            ", isAtStartDestination: ${isAtStartDestination}, " +
//                            "navController currentBackStackEntry: ${navController!!.currentBackStackEntry!!.destination}\n" +
//                            "CURRENT DEST: ${currentDestination!!},  CURRENT DEST ID: ${currentDestination.id}, " +
//                            "START DEST: ${navController!!.graph.startDestination}"
//                )
            }

            // Check if it's the root of nested fragments in this navhost
            if (navController?.currentDestination?.id == navController?.graph?.startDestination) {

                Toast.makeText(requireContext(), "⏰ AT START DESTINATION ", Toast.LENGTH_SHORT)
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

            Toast.makeText(
                requireContext(),
                "⏰ ${this.javaClass.simpleName} backStackEntryCount: $backStackEntryCount\n" +
                        "isAtStartDestination:  $isAtStartDestination, \n" +
                        "isVisible:  $isVisible, \n" +
                        "CURRENT DEST:  ${currentDestination!!.id}, " +
                        "START DEST: ${navController!!.graph.startDestination}",
                Toast.LENGTH_SHORT
            ).show()

        }
    }

}