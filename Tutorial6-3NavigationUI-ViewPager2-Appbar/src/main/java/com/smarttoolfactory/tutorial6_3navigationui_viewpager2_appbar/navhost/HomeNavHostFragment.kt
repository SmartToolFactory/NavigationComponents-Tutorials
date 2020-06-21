package com.smarttoolfactory.tutorial6_3navigationui_viewpager2_appbar.navhost

import android.content.Context
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
import com.smarttoolfactory.tutorial6_3navigationui_viewpager2_appbar.R
import com.smarttoolfactory.tutorial6_3navigationui_viewpager2_appbar.blankfragment.BaseDataBindingFragment
import com.smarttoolfactory.tutorial6_3navigationui_viewpager2_appbar.databinding.FragmentNavhostHomeBinding


/**
 * Navigation host fragment for the Home tab.
 *
 * * [findNavController] returns the main navController, not the one required for navigating
 * nested [Fragment]s. That navController is retrieved from [NavHostFragment] of this fragment.
 *
 * * Navigation graph of this fragment uses [HomeFragment1] so it navigates to it and
 * back stack entry count is one when  [HomeFragment1] is displayed
 */
class HomeNavHostFragment : BaseDataBindingFragment<FragmentNavhostHomeBinding>() {
    override fun getLayoutRes(): Int = R.layout.fragment_navhost_home

    var navController: NavController? = null


    private val nestedNavHostFragmentId = R.id.nested_nav_host_fragment_home
    val navGraphId = R.navigation.nav_graph_home

    init {
        val nestedNavHostFragment =
            childFragmentManager.findFragmentById(nestedNavHostFragmentId) as? NavHostFragment
        navController = nestedNavHostFragment?.navController
        println("HOMENAVHOST INIT navController: $navController")
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        println("🏠 ${this.javaClass.simpleName} onAttach() ${this.javaClass.simpleName} #${this.hashCode()}")

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        println("🏠 ${this.javaClass.simpleName} onCreate() ${this.javaClass.simpleName} #${this.hashCode()}")

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        println("🏠 ${this.javaClass.simpleName} onCreateView() ${this.javaClass.simpleName} #${this.hashCode()}")
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

            Toast.makeText(
                requireContext(),
                "🏠 ${this.javaClass.simpleName} ChildFragmentManager backStackEntryCount: $backStackEntryCount",
                Toast.LENGTH_SHORT
            ).show()
        }

    }

    private fun listenOnBackPressed() {
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
    }

    override fun onResume() {
        super.onResume()
        println("🏠 ${this.javaClass.simpleName} onResume()")
        callback.isEnabled = true
    }

    override fun onPause() {
        super.onPause()
        println("🏠 ${this.javaClass.simpleName} onPause()")
        callback.isEnabled = false
    }

    override fun onDestroyView() {
        super.onDestroyView()
        println("🏠 ${this.javaClass.simpleName} onDestroyView()")
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


            // Check if it's the root of nested fragmnents in this navhost
            if (navController?.currentDestination?.id == navController?.graph?.startDestination) {

                Toast.makeText(requireContext(), "🏠 AT START DESTINATION ", Toast.LENGTH_SHORT)
                    .show()
                remove()
                requireActivity().onBackPressed()
            } else {
                navController?.navigateUp()
            }

            Toast.makeText(
                requireContext(),
                "🏠 ${this.javaClass.simpleName} backStackEntryCount: $backStackEntryCount\n" +
                        "isAtStartDestination:  $isAtStartDestination, \n" +
                        "isVisible:  $isVisible, \n" +
                        "CURRENT DEST:  ${currentDestination!!.id}, " +
                        "START DEST: ${navController!!.graph.startDestination}",
                Toast.LENGTH_SHORT
            ).show()

        }
    }

}