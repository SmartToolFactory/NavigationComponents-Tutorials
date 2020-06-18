package com.smarttoolfactory.tutorial3_1navigationui_menu_buttons

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupActionBarWithNavController

/**
 * This Activity uses Toolbar menu options to navigate to specified destinations in nav_graph
 *
 * 🔥🔥 <fragment android:id="@+id/detail_dest"> in nav_graph should be EQUAL to <item android:id="@+id/detail_dest"/> in menu
 * to navigate specified fragments
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

        // TODO Shows back arrow even on main fragment use setupWithNavController for correct behaviour
//        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Get NavController
        val navController = findNavController(R.id.nav_host_fragment)

        // Get App Configuration from nav graph
        appBarConfiguration = AppBarConfiguration(navController.graph)

        /*
            Handles arrow back button
          */

        // TODO Alternative 1 Handle with Toolbar
        // 🔥 Original NavigationUI to set up with Toolbar
        //        setupActionBarWithNavController(navController, appBarConfiguration)
        // 🔥 Extension Function to set up with Toolbar
//        toolbar.setupWithNavController(navController, appBarConfiguration)
//        NavigationUI.setupWithNavController(toolbar, navController, appBarConfiguration)

        // TODO Alternative 2 Handle with Appbar by overriding onSupportNavigateUp() function
//        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration)
        setupActionBarWithNavController(navController, appBarConfiguration)
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        val navController = findNavController(R.id.nav_host_fragment)
        // Navigates to destination which is both has the same id in menu.xml and nav_graph.xml.
        return item.onNavDestinationSelected(navController)
                || super.onOptionsItemSelected(item)
    }

    // TODO Alternative 2 Handle with Appbar
    // This function is required with appbar to handle back button
    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}

