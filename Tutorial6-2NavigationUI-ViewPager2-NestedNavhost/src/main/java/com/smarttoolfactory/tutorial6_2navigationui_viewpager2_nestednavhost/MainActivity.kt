package com.smarttoolfactory.tutorial6_2navigationui_viewpager2_nestednavhost

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.viewpager2.widget.ViewPager2
import com.smarttoolfactory.tutorial6_2naigationui_viewpager2_nestednavhost.R
import com.smarttoolfactory.tutorial6_2navigationui_viewpager2_nestednavhost.blankfragment.LoginFragment1
import com.smarttoolfactory.tutorial6_2navigationui_viewpager2_nestednavhost.viewpagerfragment.ViewPagerContainerFragment
import com.smarttoolfactory.tutorial6_2navigationui_viewpager2_nestednavhost.navhost.DashboardNavHostFragment
import com.smarttoolfactory.tutorial6_2navigationui_viewpager2_nestednavhost.navhost.HomeNavHostFragment


/*
   *** Navigation Architecture ***

     MainActivity (Appbar + Toolbar)
        |- MainNavHost
           |
           |- ViewPagerContainerFragment(ViewPager2 + TabLayout)
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
 * Example that uses [ViewPager2] with tabs and each page with it's own back stack
 * and navigation.
 *
 * * [NavHostFragment] fragment such as [HomeNavHostFragment] create their child fragments
 * and can navigate in them
 *
 * * [HomeNavHostFragment] uses graph with fragment that is displayed
 * * [DashboardNavHostFragment] uses graph with itself as start destination so it should
 * check for the [NavController.getCurrentDestination] to navigate to it when device rotated
 *
 * * [LoginFragment1] is added to main graph, because of that appbar back navigation only works
 * with the [ViewPagerContainerFragment]'s [NavController]
 *
 */
class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Create Toolbar and set support action bar
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        // Get NavController
        val navController = findNavController(R.id.nav_host_fragment)

        // Get App Configuration from nav graph
        appBarConfiguration = AppBarConfiguration(navController.graph)

        // Handles arrow back button
        setupActionBarWithNavController(navController, appBarConfiguration)

        listenBackStackChange()
    }


    // This function is required with appbar to handle back button
    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
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