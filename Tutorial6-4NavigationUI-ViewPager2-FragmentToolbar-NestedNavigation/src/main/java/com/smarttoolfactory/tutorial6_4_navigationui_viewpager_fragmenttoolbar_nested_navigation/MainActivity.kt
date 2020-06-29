package com.smarttoolfactory.tutorial6_4_navigationui_viewpager_fragmenttoolbar_nested_navigation

import android.os.Bundle
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.NavHostFragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.smarttoolfactory.tutorial6_4_navigationui_viewpager_fragmenttoolbar.R
import com.smarttoolfactory.tutorial6_4_navigationui_viewpager_fragmenttoolbar.databinding.ActivityMainBinding
import com.smarttoolfactory.tutorial6_4_navigationui_viewpager_fragmenttoolbar_nested_navigation.viewpagerfragment.ViewPagerContainerFragment


/*
   *** Navigation Architecture ***

     MainActivity
        |- MainNavHost
           |
           |- ViewPagerContainerFragment(ViewPager)
               |
               |- HomeNavHostFragment(Appbar + Toolbar)
               |  |- HF1 -> HF2 -> HF3
               |
               |- DashboardNavHostFragment(Appbar Toolbar)
               |  |- DF1 -> DF2 -> DF3
               |
               |- NotificationHostFragment(Appbar Toolbar)
                  |- NF1 -> NF2 -> NF3

 */

/**
 *  In this example each [NavHostFragment] has it's own toolbar
 *  They can navigate back with back arrow when navigated to an inner fragment of ViewPager
 *
 *  ### Using [FragmentStateAdapter.registerFragmentTransactionCallback] with [FragmentStateAdapter] solves back navigation instead of using [OnBackPressedCallback.handleOnBackPressed] in every [NavHostFragment]
 * ### Should set app:defaultNavHost="true" for [NavHostFragment] for this to work

 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val dataBinding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)

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