package com.smarttoolfactory.navigation1navgraph

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.smarttoolfactory.navigationworkmanagerpagination.R

/**
 * Basic Navigation Component example. [MainFragment] has top, bottom, and middle fragments to navigate to.
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

        // Get NavHostFragment
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment)

        // ChildFragmentManager of NavHostFragment
        val navHostChildFragmentManager =  navHostFragment?.childFragmentManager

        navHostChildFragmentManager?.addOnBackStackChangedListener {

            val backStackEntryCount = navHostChildFragmentManager.backStackEntryCount
            val fragments = navHostChildFragmentManager.fragments

            println("😛 NavHost count: $backStackEntryCount, fragments: $fragments")

        }

        /*

            If we go to B1->B2->B3
            Prints:

            😛 NavHost count: 1, fragments: [MiddleFirstFragment{1013559} (9585bc84-f6e7-45e0-a197-41c00f49a553) id=0x7f0800ca}]
            😛 NavHost count: 2, fragments: [MiddleSecondFragment{41904c7} (b38a1c3b-ff55-44a5-8471-885e427cb24b) id=0x7f0800ca}]
            😛 NavHost count: 3, fragments: [MiddleThirdFragment{41ec984} (0836f0dd-b22c-4f03-b63d-48c35c3f69c8) id=0x7f0800ca}]

         */

    }

}