package com.smarttoolfactory.tutorial6_2navigationui_viewpager2_nestednavhost.navhost

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.smarttoolfactory.tutorial6_2naigationui_viewpager2_nestednavhost.R
import com.smarttoolfactory.tutorial6_2naigationui_viewpager2_nestednavhost.databinding.FragmentNavhostHomeBinding
import com.smarttoolfactory.tutorial6_2navigationui_viewpager2_nestednavhost.blankfragment.BaseDataBindingFragment
import com.smarttoolfactory.tutorial6_2navigationui_viewpager2_nestednavhost.blankfragment.HomeFragment1


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
    private val navGraphId = R.navigation.nav_graph_home

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /*
            🔥 This is navController we get from findNavController not the one required
            for navigating nested fragments
         */
        val mainNavController =
            Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)

        val nestedNavHostFragment =
            childFragmentManager.findFragmentById(nestedNavHostFragmentId) as? NavHostFragment
        navController = nestedNavHostFragment?.navController

        Toast.makeText(
            requireContext(),
            "mainNavController equals findNavController ${findNavController() == mainNavController}",
            Toast.LENGTH_SHORT
        ).show()

        println(
            "🏠 ${this.javaClass.simpleName} onViewCreated(): ${this.javaClass.simpleName} #${this.hashCode()}\n" +
                    "navController: $navController, findNavController(): ${findNavController()}, mainNavController: $mainNavController\n" +
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
                    " 🏠 ${this.javaClass.simpleName} handleOnBackPressed() " +
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

            println("🏠 ${this@HomeNavHostFragment.javaClass.simpleName} #${this@HomeNavHostFragment.hashCode()} handleOnBackPressed()")


            // Get NavHostFragment
            val navHostFragment =
                childFragmentManager.findFragmentById(nestedNavHostFragmentId)
            // ChildFragmentManager of the current NavHostFragment
            val navHostChildFragmentManager = navHostFragment?.childFragmentManager

            val currentDestination = navController?.currentDestination
            val backStackEntryCount = navHostChildFragmentManager!!.backStackEntryCount

            val isAtStartDestination =
                (navController?.currentDestination?.id == navController?.graph?.startDestination)

            // Returns only the fragment on top, fragmentManager uses REPLACE
            val fragments = navHostChildFragmentManager.fragments
            fragments.forEach {
//                println(
//                    "🏠 ${this.javaClass.simpleName} handleOnBackPressed() " +
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



/*
    🔥🔥🔥 LifeCycle after rotation device and touching back button on HomeFragment3

    Despite the fact HomeFragment3 back callback should be called it calls
    ⏰ NotificationHostFragment #209725945 handleOnBackPressed()
    because after rotation NotificationHostFragment is re-created but, it's onResume is not called
    since it's not on screen. It's destroyed after 5 seconds but, if back is pressed during that
    interval it causes unexpected behavior.

    🔥🔥🔥 Set back press CALLBACK with FALSE instead of TRUE to set it disabled until it's on screen

    I: 😱 HomeFragment2 #141473759 onPause()
    I: 😱 HomeNavHostFragment #179584710 onPause()
    I: 😱 ViewPagerContainerFragment #184916561 onPause()
    I: 🥵 HomeFragment2 #141473759  onDestroyView()
    I: 🥵 HomeNavHostFragment #179584710  onDestroyView()
    I: 🥵 DashboardFragment1 #166784030  onDestroyView()
    I: 🥵 DashBoardNavHostFragment #192326009  onDestroyView()
    I: 🥵 NotificationFragment2 #24673958  onDestroyView()
    I: 🥵 NotificationHostFragment #110785497  onDestroyView()
    I: 🥵 ViewPagerContainerFragment #184916561  onDestroyView()
    I: 🥶 HomeFragment2 #141473759  onDestroy()
    I: 🥶 HomeFragment1 #95596392  onDestroy()
    I: 🥶 HomeNavHostFragment #179584710  onDestroy()
    I: 🥶 DashboardFragment1 #166784030  onDestroy()
    I: 🥶 DashBoardNavHostFragment #108537295  onDestroy()
    I: 🥶 DashBoardNavHostFragment #192326009  onDestroy()
    I: 🥶 NotificationFragment2 #24673958  onDestroy()
    I: 🥶 NotificationFragment1 #190126439  onDestroy()
    I: 🥶 NotificationHostFragment #110785497  onDestroy()
    I: 🥶 ViewPagerContainerFragment #184916561  onDestroy()
    I: 😀 HomeFragment2 #95178144  onCreate()
    I: 😀 HomeFragment1 #254678549  onCreate()
    I: 😀 HomeNavHostFragment #20098315  onCreate()
    I: 😀 DashboardFragment1 #48513781  onCreate()
    I: 😀 DashBoardNavHostFragment #66614102  onCreate()
    I: 😀 DashBoardNavHostFragment #56640244  onCreate()
    I: 😀 NotificationFragment2 #148133046  onCreate()
    I: 😀 NotificationFragment1 #143390291  onCreate()
    I: 😀 NotificationHostFragment #209725945  onCreate()
    I: 😀 ViewPagerContainerFragment #255793055  onCreate()
    I: 🤣 ViewPagerContainerFragment #255793055 onCreateView()
    I: 🔥 ViewPagerContainerFragment #255793055  onActivityCreated()
    I: 🤣 HomeNavHostFragment #20098315 onCreateView()
    I: 🏠 HomeNavHostFragment onViewCreated(): HomeNavHostFragment #20098315
    I: navController: androidx.navigation.NavHostController@448154c, findNavController(): androidx.navigation.NavHostController@df66995, mainNavController: androidx.navigation.NavHostController@df66995
    I: navController currentBackStackEntry: Destination(com.smarttoolfactory.tutorial6_2naigationui_viewpager2_nestednavhost:id/homeFragment2) label=HomeFragment2 class=com.smarttoolfactory.tutorial6_2navigationui_viewpager2_nestednavhost.blankfragment.HomeFragment2
    I: currentDestination: Destination(com.smarttoolfactory.tutorial6_2naigationui_viewpager2_nestednavhost:id/homeFragment2) label=HomeFragment2 class=com.smarttoolfactory.tutorial6_2navigationui_viewpager2_nestednavhost.blankfragment.HomeFragment2
    I: current dest id: 2131230880, startDestination: 2131230879, graph start dest: 2131230879
    I: 🔥 HomeNavHostFragment #20098315  onActivityCreated() HomeNavHostFragment{132ad0b} (72469e3a-876d-4039-b024-45822e33487f) f0}
    I: 🤣 HomeFragment2 #95178144 onCreateView()
    I: 🔥 HomeFragment2 #95178144  onActivityCreated() HomeFragment2{5ac4da0} (ccd7bc73-b3c2-42bd-9100-9b30516cb6ab) id=0x7f0800db}
    I: 🤣 DashBoardNavHostFragment #56640244 onCreateView()
    I: 🏂 DashBoardNavHostFragment onViewCreated(): DashBoardNavHostFragment #56640244
    I: navController: androidx.navigation.NavHostController@9bcc798, findNavController(): androidx.navigation.NavHostController@df66995, mainNavController: androidx.navigation.NavHostController@df66995
    I: navController currentBackStackEntry: Destination(com.smarttoolfactory.tutorial6_2naigationui_viewpager2_nestednavhost:id/dashboardFragment1) label=DashboardFragment1 class=com.smarttoolfactory.tutorial6_2navigationui_viewpager2_nestednavhost.blankfragment.DashboardFragment1
    I: currentDestination: Destination(com.smarttoolfactory.tutorial6_2naigationui_viewpager2_nestednavhost:id/dashboardFragment1) label=DashboardFragment1 class=com.smarttoolfactory.tutorial6_2navigationui_viewpager2_nestednavhost.blankfragment.DashboardFragment1
    I: current dest id: 2131230832, startDestination: 2131230835, graph start dest: 2131230835
    I: 🔥 DashBoardNavHostFragment #56640244  onActivityCreated()
    I: 🤣 DashboardFragment1 #48513781 onCreateView()
    I: 🔥 DashboardFragment1 #48513781  onActivityCreated()
    I: 🤣 NotificationHostFragment #209725945 onCreateView()
    I: ⏰ NotificationHostFragment onViewCreated(): NotificationHostFragment #209725945
    I: navController currentBackStackEntry: Destination(com.smarttoolfactory.tutorial6_2naigationui_viewpager2_nestednavhost:id/notificationFragment2) label=NotificationFragment2 class=com.smarttoolfactory.tutorial6_2navigationui_viewpager2_nestednavhost.blankfragment.NotificationFragment2
    I: currentDestination: Destination(com.smarttoolfactory.tutorial6_2naigationui_viewpager2_nestednavhost:id/notificationFragment2) label=NotificationFragment2 class=com.smarttoolfactory.tutorial6_2navigationui_viewpager2_nestednavhost.blankfragment.NotificationFragment2
    I: current dest id: 2131230946, startDestination: 2131230945, graph start dest: 2131230945
    I: 🔥 NotificationHostFragment #209725945  onActivityCreated()
    I: 🤣 NotificationFragment2 #148133046 onCreateView()
    I: 🔥 NotificationFragment2 #148133046  onActivityCreated()
    I: 🎃 ViewPagerContainerFragment #255793055 onResume()
    I: 🎃 HomeNavHostFragment #20098315 onResume()
    I: 🎃 HomeFragment2 #95178144 onResume()
    I: ⏰ NotificationHostFragment #209725945 handleOnBackPressed()
    I: ⏰ NotificationHostFragment #209725945 handleOnBackPressed()
    I: 🥵 DashboardFragment1 #48513781  onDestroyView()
    I: 🥵 DashBoardNavHostFragment #56640244  onDestroyView()
    I: 🥶 DashboardFragment1 #48513781  onDestroy()
    I: 🥶 DashBoardNavHostFragment #66614102  onDestroy()
    I: 🥶 DashBoardNavHostFragment #56640244  onDestroy()
    I: 🥵 NotificationFragment2 #148133046  onDestroyView()
    I: 🥵 NotificationHostFragment #209725945  onDestroyView()
    I: 🥶 NotificationFragment2 #148133046  onDestroy()
    I: 🥶 NotificationFragment1 #143390291  onDestroy()
    I: 🥶 NotificationHostFragment #209725945  onDestroy()
 */