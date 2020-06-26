package com.smarttoolfactory.tutorial6_6navigationui_viewpager2_appbar_mixednavigation_viewmodel

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.smarttoolfactory.tutorial6_6navigationui_viewpager2_appbar_mixednavigation_viewmodel.blankfragment.LoginFragment2
import com.smarttoolfactory.tutorial6_6navigationui_viewpager2_appbar_mixednavigation_viewmodel.navhost.ParentNavHostFragment
import com.smarttoolfactory.tutorial6_6navigationui_viewpager2_appbar_mixednavigation_viewmodel.viewmodel.AppbarViewModel
import com.smarttoolfactory.tutorial6_6navigationui_viewpager2_appbar_mixednavigation_viewmodel.viewpagerfragment.ViewPagerContainerFragment

/*
   *** Navigation Architecture ***

     MainActivity
        |- MainNavHost
           |
           |- ParenNavHost((Appbar + Toolbar)
               |
               |- ViewPagerContainerFragment(TabLayout + ViewPager2)
               |   |
               |   |- HomeNavHostFragment
               |   |  |- HF1 -> HF2 -> HF3
               |   |
               |   |- DashboardNavHostFragment
               |   |  |- DF1 -> DF2 -> DF3
               |   |
               |   |- NotificationHostFragment
               |   |  |- NF1 -> NF2 -> NF3
               |   |
               |   |-LoginFragment1
               |
               |- LoginFragment1 -> LoginFragment2


 */

/**
 *  In this example only [ParentNavHostFragment] has Appbar and Toolbar, navigation of individual
 *  NavHostFragments is done via [AppbarViewModel.currentNavController] liveData which
 *  returns the visible [NavController] of current [NavHostFragment]
 *  due to setting it in [NavHostFragment.onResume]
 *
 *  * [ParentNavHostFragment]'s role is to have it's own Appbar to contain login fragments and
 *  navigate through them using Appbar. Without [ParentNavHostFragment] we navigate
 *  to [LoginFragment2] that has no Appbar if it's in [ViewPagerContainerFragment].
 *
 *  It can be done by putting Appbar to [MainActivity] but purpose here is to put
 *  Appbar + Toolbar inside a fragment to be able to use with [BottomNavigationView] for instance
 *
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listenBackStackChange()
    }


    private fun listenBackStackChange() {
        // Get NavHostFragment
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment)

        // ChildFragmentManager of NavHostFragment
        val navHostChildFragmentManager = navHostFragment?.childFragmentManager

        navHostChildFragmentManager?.addOnBackStackChangedListener {

            val backStackEntryCount = navHostChildFragmentManager.backStackEntryCount
            val fragments = navHostChildFragmentManager.fragments
            val fragmentCount = fragments.size

            println("🎃 Main graph backStackEntryCount: $backStackEntryCount, fragmentCount: $fragmentCount, fragments: $fragments")

            Toast.makeText(
                this,
                "🎃 Main graph backStackEntryCount: $backStackEntryCount, fragmentCount: $fragmentCount, fragments: $fragments",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}