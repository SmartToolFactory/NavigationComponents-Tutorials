package com.smarttoolfactory.tutorial1_3navigation_nestednavhost

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment


/**
 * Example that displays how nested graphs with their own back stack or [NavHostFragment] work.
 *
 * **Notes:**
 * * Main graph back stack is controlled by [NavHostFragment.getChildFragmentManager]
 *
 * * When a nested navigation graph or [NavHostFragment] added also it's back stack is retrieved
 * using childFragmentManager.
 *
 * * When on nested graph back button navigates from that back stack to current entry position on
 * main graph.
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
            supportFragmentManager.findFragmentById(R.id.main_nav_host_fragment)

        // ChildFragmentManager of NavHostFragment
        val navHostChildFragmentManager = navHostFragment?.childFragmentManager

        navHostChildFragmentManager?.addOnBackStackChangedListener {

            val backStackEntryCount = navHostChildFragmentManager.backStackEntryCount
            val fragments = navHostChildFragmentManager.fragments

            println("🎃 Main graph backStackEntryCount: $backStackEntryCount, fragments: $fragments")

            Toast.makeText(
                this,
                "🎃 Main graph backStackEntryCount: $backStackEntryCount, fragments: $fragments",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}
