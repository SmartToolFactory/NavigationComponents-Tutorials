package com.smarttoolfactory.tutorial1_3navigation_nestednavhost

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Get NavHostFragment
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.main_nav_host_fragment)

        // ChildFragmentManager of NavHostFragment
        val navHostChildFragmentManager = navHostFragment?.childFragmentManager

        navHostChildFragmentManager?.addOnBackStackChangedListener {

            val backStackEntryCount = navHostChildFragmentManager.backStackEntryCount
            val fragments = navHostChildFragmentManager.fragments

            println("😛 NavHost count: $backStackEntryCount, fragments: $fragments")

            Toast.makeText(
                this,
                "😛 NavHost count: $backStackEntryCount, fragments: $fragments",
                Toast.LENGTH_SHORT
            ).show()

        }
    }
}
