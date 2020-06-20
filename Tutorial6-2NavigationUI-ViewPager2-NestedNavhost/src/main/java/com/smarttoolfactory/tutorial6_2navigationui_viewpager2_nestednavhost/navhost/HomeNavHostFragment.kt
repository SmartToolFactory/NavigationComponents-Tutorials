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

class HomeNavHostFragment : BaseDataBindingFragment<FragmentNavhostHomeBinding>() {
    override fun getLayoutRes(): Int = R.layout.fragment_navhost_home

    private var navController: NavController? = null

    private val nestedNavHostFragmentId = R.id.nested_nav_host_fragment_home
    private val navGraphId = R.navigation.nav_graph_home


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        println("😱 HomeNavHostFragment onCreateView() this: ${this.hashCode()}")
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val nestedNavHostFragment =
            childFragmentManager.findFragmentById(nestedNavHostFragmentId) as? NavHostFragment
        navController = nestedNavHostFragment?.navController

        /*
            🔥 Alternative 1 This one re-creates a new stack over existing one,
            but previous stack of fragments is intact and states kept
         */
//        if (navController!!.currentDestination!!.id == navController!!.graph.startDestination) {
//            navController?.navigate(R.id.homeFragment1)
//        }

        // 🔥 Alternative 2
//        val navInflater = navController!!.navInflater
//        nestedNavHostFragment!!.navController.graph = graph
//        val graph = navController!!.navInflater.inflate(navGraphId)
//        nestedNavHostFragment!!.navController.graph = graph


        println(
            "✅️ HomeNavHostFragment onViewCreated(): ${this.javaClass.simpleName} #${this.hashCode()}\n" +
                    "currentDestination: ${navController!!.currentDestination}\n" +
                    "dest id: ${navController!!.currentDestination!!.id}, " +
                    "startDestination: ${navController!!.graph.startDestination}, " +
                    "graph start dest: ${navController!!.navInflater.inflate(navGraphId).startDestination}"
        )

        // listen back stack changes for this NavHost
        listenBackStack()

        // Listen on back press
        listenOnBackPressed()

    }

    override fun onDestroyView() {
        super.onDestroyView()

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
                    " ⛱ HomeNavHost handleOnBackPressed() " +
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
                "😍 HomeNavHostFragment ChildFragmentManager backStackEntryCount: $backStackEntryCount",
                Toast.LENGTH_SHORT
            ).show()
        }

    }

    private fun listenOnBackPressed() {

        // Get NavHostFragment
        val navHostFragment =
            childFragmentManager.findFragmentById(nestedNavHostFragmentId)
        // ChildFragmentManager of the current NavHostFragment
        val navHostChildFragmentManager = navHostFragment?.childFragmentManager


        val callback = object : OnBackPressedCallback(true) {

            override fun handleOnBackPressed() {

                val currentDestination = navController?.currentDestination
                val backStackEntryCount = navHostChildFragmentManager!!.backStackEntryCount


                Toast.makeText(
                    requireContext(),
                    "😡 HomeNavHost backStackEntryCount: $backStackEntryCount",
                    Toast.LENGTH_SHORT
                ).show()


                val fragments = navHostChildFragmentManager.fragments

                fragments.forEach {
                    println(
                        " ⛱ HomeNavHost handleOnBackPressed() " +
                                "fragment: ${it.javaClass.simpleName} #${it.hashCode()}," +
                                "backStackEntryCount: $backStackEntryCount, " +
                                " isVisible: ${it.isVisible}, " +
                                ", isResumed: ${it.isResumed}, " +
                                "currentDestination: ${currentDestination!!},  DEST ID: ${currentDestination.id}, " +
                                "startDestination: ${navController!!.graph.startDestination}"

                    )
                }


//                if (backStackEntryCount == 1) {
//                    // We are at the root of nested navigation, remove this callback
//                    remove()
//                    requireActivity().onBackPressed()
//                } else {
//                    navController?.navigateUp()
//                }

                navController?.navigateUp()

            }
        }

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)

    }
}