package com.smarttoolfactory.tutorial1_3navigation_nestednavhost.navhost

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.smarttoolfactory.tutorial1_3navigation_nestednavhost.R
import com.smarttoolfactory.tutorial1_3navigation_nestednavhost.blankfragment.BaseDataBindingFragment
import com.smarttoolfactory.tutorial1_3navigation_nestednavhost.databinding.FragmentNavhostHomeBinding


/*
    Creation of this Fragment  -> Navigating Fragment 3

    Prints:
    I: 😱 HomeNavHostFragment onCreateView() this: 201156203
    I: 🤣 BaseDataBindingFragment onCreateView() HomeNavHostFragment #201156203
    I: ✅️ HomeNavHostFragment onViewCreated(): HomeNavHostFragment #201156203
    I: navController currentBackStackEntry: Destination(nestednavhost:id/home_dest) label=HomeHost class=nestednavhost.navhost.HomeNavHostFragment
    I: currentDestination: Destination(nestednavhost:id/home_dest) label=HomeHost class=nestednavhost.navhost.HomeNavHostFragment
    I: current dest id: 2131230875, startDestination: 2131230875, graph start dest: 2131230875

     🤣 BaseDataBindingFragment onCreateView() HomeFragment1 #161310760
    I:  ⛱ HomeNavHost handleOnBackPressed() fragment: HomeFragment1 #161310760,backStackEntryCount: 1,  isVisible: true,  isVisible: true, , isResumed: false, navController currentBackStackEntry: Destination(nestednavhost:id/homeFragment1) label=HomeFragment1 class=nestednavhost.blankfragment.HomeFragment1
    I: currentDestination: Destination(nestednavhost:id/homeFragment1) label=HomeFragment1 class=nestednavhost.blankfragment.HomeFragment1, DEST ID: 2131230872, startDestination: 2131230875
    I: 🎃 Main graph backStackEntryCount: 1, fragments: [HomeNavHostFragment{bfd666b} (f322b3d5-2168-46e0-83e6-a7380ba452ed) id=0x7f0800ac}]

   // Second Fragment is Opened
    I: 🤣 BaseDataBindingFragment onCreateView() HomeFragment2 #205452602
    I:  ⛱ HomeNavHost handleOnBackPressed() fragment: HomeFragment2 #205452602,backStackEntryCount: 2,  isVisible: true,  isVisible: true, , isResumed: true, navController currentBackStackEntry: Destination(nestednavhost:id/homeFragment2) label=HomeFragment2 class=nestednavhost.blankfragment.HomeFragment2
    I: currentDestination: Destination(nestednavhost:id/homeFragment2) label=HomeFragment2 class=nestednavhost.blankfragment.HomeFragment2, DEST ID: 2131230873, startDestination: 2131230875

      // Third Fragment is Opened
    I: 🤣 BaseDataBindingFragment onCreateView() HomeFragment3 #249676808
    I:  ⛱ HomeNavHost handleOnBackPressed() fragment: HomeFragment3 #249676808,backStackEntryCount: 3,  isVisible: true,  isVisible: true, , isResumed: true, navController currentBackStackEntry: Destination(nestednavhost:id/homeFragment3) label=HomeFragment3 class=nestednavhost.blankfragment.HomeFragment3
    I: currentDestination: Destination(nestednavhost:id/homeFragment3) label=HomeFragment3 class=nestednavhost.blankfragment.HomeFragment3, DEST ID: 2131230874, startDestination: 2131230875

 */
class HomeNavHostFragment : BaseDataBindingFragment<FragmentNavhostHomeBinding>() {
    override fun getLayoutRes(): Int = R.layout.fragment_navhost_home

    private var navController: NavController? = null

    private val nestedNavHostFragmentId = R.id.nested_nav_host_fragment
    private val navGraphId = R.navigation.nav_graph_home


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

        println(
            "✅️ HomeNavHostFragment onViewCreated(): ${this.javaClass.simpleName} #${this.hashCode()}\n" +
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
         */
        if (
            navController!!.currentDestination == null ||
            navController!!.currentDestination!!.id == navController!!.graph.startDestination
        ) {
            navController?.navigate(R.id.homeFragment1)
        }

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
        listenBackStack()

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
            val currentDestination = navController?.currentDestination

            // Returns only the fragment on top
            val fragments = navHostChildFragmentManager.fragments
            fragments.forEach {

                println(
                    " ⛱ HomeNavHost addOnBackStackChangedListener() " +
                            "fragment: ${it.javaClass.simpleName} #${it.hashCode()}," +
                            "backStackEntryCount: $backStackEntryCount, " +
                            " isVisible: ${it.isVisible}, " +
                            " isVisible: ${it.isVisible}, " +
                            ", isResumed: ${it.isResumed}, " +
                            "navController currentBackStackEntry: ${navController!!.currentBackStackEntry!!.destination}\n" +
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

    override fun onResume() {
        super.onResume()
        callback.isEnabled = true
    }

    override fun onPause() {
        super.onPause()
        callback.isEnabled = false
    }

    private fun listenOnBackPressed() {

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
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

            // Get NavHostFragment
            val navHostFragment =
                childFragmentManager.findFragmentById(nestedNavHostFragmentId)
            // ChildFragmentManager of the current NavHostFragment
            val navHostChildFragmentManager = navHostFragment?.childFragmentManager

            val currentDestination = navController?.currentDestination
            val backStackEntryCount = navHostChildFragmentManager!!.backStackEntryCount

            // Returns only the fragment on top
            val fragments = navHostChildFragmentManager.fragments
            fragments.forEach {
                println(
                    " 😑 HomeNavHost handleOnBackPressed() " +
                            "fragment: ${it.javaClass.simpleName} #${it.hashCode()}," +
                            "backStackEntryCount: $backStackEntryCount, " +
                            " isVisible: ${it.isVisible}, " +
                            ", isResumed: ${it.isResumed}, " +
                            "navController currentBackStackEntry: ${navController!!.currentBackStackEntry!!.destination}\n" +
                            "currentDestination: ${currentDestination!!},  DEST ID: ${currentDestination.id}, " +
                            "startDestination: ${navController!!.graph.startDestination}"
                )
            }

            Toast.makeText(
                requireContext(),
                "😡 HomeNavHost backStackEntryCount: $backStackEntryCount",
                Toast.LENGTH_SHORT
            ).show()


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