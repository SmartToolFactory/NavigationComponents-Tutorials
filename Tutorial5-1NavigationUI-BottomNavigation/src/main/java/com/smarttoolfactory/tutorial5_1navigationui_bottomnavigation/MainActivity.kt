package com.smarttoolfactory.tutorial5_1navigationui_bottomnavigation

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.addOnBackStackChangedListener {
            val backStackEntryCount = supportFragmentManager.backStackEntryCount
            val fragments = supportFragmentManager.fragments
            val fragmentCount = fragments.size


            Toast.makeText(
                this,
                "MainActivity backStackEntryCount: $backStackEntryCount, fragmentCount: $fragmentCount, fragments: $fragments",
                Toast.LENGTH_SHORT
            ).show()
        }

        val navController = findNavController(R.id.nav_host_fragment)

        val bottomNav = findViewById<BottomNavigationView>(R.id.nav_view)

        // TODO Alternative 1 to set bottom navigation
        bottomNav?.setupWithNavController(navController)

        // TODO Alternative 2 to set bottom navigation
//        NavigationUI.setupWithNavController(bottomNav, navController)

        bottomNav.setOnNavigationItemSelectedListener {
            NavigationUI.onNavDestinationSelected(it, navController)
        }
    }

}