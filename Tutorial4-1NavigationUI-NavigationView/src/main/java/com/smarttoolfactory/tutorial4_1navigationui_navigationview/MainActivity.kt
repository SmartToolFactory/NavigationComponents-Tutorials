package com.smarttoolfactory.tutorial4_1navigationui_navigationview

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.*
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val drawerLayout: DrawerLayout? = findViewById(R.id.drawer_layout)

        // Create Toolbar and set support action bar
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        // ⚠️ This is not required for Navigation view, back arrow or hamburger button
        // ⚠️ Only Needed for displaying menu icons
        setSupportActionBar(toolbar)

        // Get NavController
        val navController = findNavController(R.id.nav_host_fragment)

        // Get App Configuration from nav graph
        appBarConfiguration = AppBarConfiguration(navController.graph)

        // 🔥 This configuration is for top-level destinations, but don't know what this is?
        // Used set of only 2 items to test but 3 of navigation items work
//        appBarConfiguration = AppBarConfiguration(
//            setOf(R.id.detail_dest, R.id.settings_dest),
//            drawerLayout
//        )

        setupNavigationMenu(navController)

        // TODO Alternative 1 Appbar to set back and hamburger
//        setupActionBar(navController, appBarConfiguration)

        // TODO Alternative 2 Toolbar to set back and hamburger
        toolbar.setupWithNavController(navController, drawerLayout)

        // This is for listening when a transition happens to a fragment and, change some features
        // Here, i set visibility of toolber when it's Camera fragment to simulate cam layout
        listenForNavigationEvents(navController, toolbar)


    }

    /**
     * Set up NavigationView actions and where to navigate to by [NavController]
     * using [NavigationUI] or it's extension functions that available in kotlin
     */
    private fun setupNavigationMenu(navController: NavController) {
        // TODO Use NavigationUI to set up a Navigation View
        // ⚠️ This does NOT modify the actionbar
        val sideNavView = findViewById<NavigationView>(R.id.nav_view)
        sideNavView?.setupWithNavController(navController)
    }


    //    *** ACTION BAR ***

    // Alternative to set Appbar, if toolbar is not available for instance
    private fun setupActionBar(
        navController: NavController,
        appBarConfig: AppBarConfiguration
    ) {
        // TODO Have NavigationUI handle what your ActionBar displays
//        // This allows NavigationUI to decide what label to show in the action bar
//        // By using appBarConfig, it will also determine whether to
//        // show the up arrow or drawer menu icon
        setupActionBarWithNavController(navController, appBarConfig)
    }

    // This method used with seupActionBar method, setting with toolBar handles back arrow and
    // hamburger button changes
    override fun onSupportNavigateUp(): Boolean {
        // Allows NavigationUI to support proper up navigation or the drawer layout
        // drawer menu, depending on the situation
        return findNavController(R.id.nav_host_fragment).navigateUp(appBarConfiguration)
    }


    //    *** LISTENING NAVIGATION CHANGES ***
    private fun listenForNavigationEvents(navController: NavController, toolbar: Toolbar) {

        //  🔥 This is the non-lambda version of the method
//        navController.addOnDestinationChangedListener(object: NavController.OnDestinationChangedListener{
//            override fun onDestinationChanged(
//                controller: NavController,
//                destination: NavDestination,
//                arguments: Bundle?) {
//
//            }
//        })

        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.camera_dest) {
                toolbar?.visibility = View.GONE
            } else {
                toolbar?.visibility = View.VISIBLE
            }
        }
    }


    //    *** MENU ***

    // Setting menu options
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

}