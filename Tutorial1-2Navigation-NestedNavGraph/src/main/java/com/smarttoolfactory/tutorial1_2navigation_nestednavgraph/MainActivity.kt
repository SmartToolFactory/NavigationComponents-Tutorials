package com.smarttoolfactory.tutorial1_2navigation_nestednavgraph

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.smarttoolfactory.tutorial1_2navigation_nestednavgraph.blankfragment.CameraFragment

/**
 * Sample to display nested or separate nav graphs.
 *
 * * nav_graph is the main graph for navigation which can directly go to [CameraFragment] or
 * **nav_graph_dashboard** or **nav_graph_home**
 *
 * * In this example this nested graphs are part of the same
 * ```navHostFragment?.childFragmentManager```
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
            supportFragmentManager.findFragmentById(R.id.main_nav_host_fragment)

        // ChildFragmentManager of NavHostFragment
        val navHostChildFragmentManager = navHostFragment?.childFragmentManager

        navHostChildFragmentManager?.addOnBackStackChangedListener {

            val backStackEntryCount = navHostChildFragmentManager.backStackEntryCount
            val fragments = navHostChildFragmentManager.fragments

            println("😛 NavHost count: $backStackEntryCount, fragments: $fragments")

        }
    }
}
