package com.smarttoolfactory.tutorial6_1navigationui_multiple_activities.navmenu

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.onNavDestinationSelected
import com.smarttoolfactory.tutorial6_1navigationui_multiple_activities.R

/**
 * This Activity uses Toolbar menu options to navigate to specified destinations in nav_graph
 *
 * 🔥🔥 <fragment android:id="@+id/detail_dest"> in nav_graph should be EQUAL to <item android:id="@+id/detail_dest"/> in menu
 * to navigate specified fragments
 *
 */
class MenuNavigationActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)

        // Create Toolbar and set support action bar
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        val navController = findNavController(R.id.nav_host_fragment_menu)

        return item.onNavDestinationSelected(navController)
                || super.onOptionsItemSelected(item)
    }
}
