package com.smarttoolfactory.tutorial6_1navigationui_multiple_activities.navnavigationview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import com.smarttoolfactory.tutorial6_1navigationui_multiple_activities.R

class NavigationViewNavigationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_navigation_view)


//         Create Toolbar and set support action bar
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        // Set top arrow
//        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Get navigation controller
        val navController = findNavController(R.id.nav_host_fragment_navigation_view)

        val appBarConfiguration = AppBarConfiguration(navController.graph)


    }
}