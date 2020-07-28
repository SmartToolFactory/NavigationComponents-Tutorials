package com.smarttoolfactory.tutorial6_0navigationui_viewpager2

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

/*
   *** Navigation Architecture ***

 MainActivity (Appbar + Toolbar)
    |- MainNavHost
        |
        | FragmentViewPagerContainer(ViewPager2 + TabLayout)
            |- HomeFragment1 -> HomeFragment2 -> HomeFragment3
            |- DashboardFragment1 -> DashboardFragment2 -> DashboardFragment3
 */

/**
 * Example to use [ViewPager2] with navigation components.
 *
 * * In this example navigation is done between main NavHost and main back stack,
 * every page/fragment in [ViewPager2] added main back stack.
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

//        listenBackStackChange()
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