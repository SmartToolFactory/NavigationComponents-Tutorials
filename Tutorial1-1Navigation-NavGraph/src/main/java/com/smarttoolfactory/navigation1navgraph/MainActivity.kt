package com.smarttoolfactory.navigation1navgraph

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.smarttoolfactory.navigationworkmanagerpagination.R

/**
 * Basic Navigation Component example. MainFragent has top, bottom, and middle fragments to navigate to.
 *
 * Top and bottom fragments only navigate forward once
 *
 * MiddleFragment1-> MiddleFragment2->MiddleFragment3->MainFragment is the navigation direction.
 *
 * Animation for transitions is added to actions in xml or added via navOptions
 *
 *
 * Steps to follow:
 *
 * * 1 Create a fragment in activity_main.xml, set  app:navGraph="@navigation/nav_graph"
 * to set graph for navigation and app:defaultNavHost="true" to be able to intercept back button.
 *
 * * 2 Create navigation folder in res and create a nav_graph_xml file
 *
 * * 3 Set where to navigate by setting actions and destinations.
 *
 * * 4 Add transition animation by setting in actions
 *
 * * 5 to Pop back to from any fragment to any point by popping fragment back stack use popTo
 * and popUpToInclusive properties of actions
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.addOnBackStackChangedListener {
            val backStackEntryCount = supportFragmentManager.backStackEntryCount
            val fragments = supportFragmentManager.fragments


            Toast.makeText(
                this,
                "MainActivity() backStackEntryCount: $backStackEntryCount, fragments size: ${fragments.size}",
                Toast.LENGTH_SHORT
            ).show()


        }
    }

}

















