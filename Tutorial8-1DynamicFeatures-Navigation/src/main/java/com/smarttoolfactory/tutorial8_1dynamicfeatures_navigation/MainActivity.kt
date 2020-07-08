package com.smarttoolfactory.tutorial8_1dynamicfeatures_navigation

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.smarttoolfactory.tutorial8_1dynamicfeatures_navigation.adapter.ActivityFragmentStateAdapter
import com.smarttoolfactory.tutorial8_1dynamicfeatures_navigation.databinding.ActivityMainBinding
import com.smarttoolfactory.tutorial8_1dynamicfeatures_navigation.fragment.blankfragment.HomeFragment1
import com.smarttoolfactory.tutorial8_1dynamicfeatures_navigation.fragment.blankfragment.HomeFragment2
import com.smarttoolfactory.tutorial8_1dynamicfeatures_navigation.util.Event
import com.smarttoolfactory.tutorial8_1dynamicfeatures_navigation.viewmodel.NavControllerViewModel


/**
 * Tutorial to navigate to dynamic feature module from App, navigate from dynamic feature module
 * gallery to favorites.
 *
 * * [HomeFragment1] listen savedStateHandle with `findNavController().currentBackStackEntry?.savedStateHandle?.getLiveData<Int>`
 *  [HomeFragment2] and GalleryFragment sets result with `findNavController().previousBackStackEntry?.savedStateHandle?.set("count", count)`
 *
 *  * Also used setFragmentResultListener, and setFragmentResult but not working
 */
class MainActivity : AppCompatActivity() {

    private val navControllerViewModel: NavControllerViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val dataBinding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)

        val viewPager2 = dataBinding.viewPager
        val bottomNavigationView = dataBinding.bottomNav

        // Cancel ViewPager swipe
        viewPager2.isUserInputEnabled = false

        // Set viewpager adapter
        viewPager2.adapter = ActivityFragmentStateAdapter(this, navControllerViewModel)

        // Listen bottom navigation tabs change
        bottomNavigationView.setOnNavigationItemSelectedListener {

            when (it.itemId) {
                R.id.nav_graph_home -> {
                    viewPager2.setCurrentItem(0, false)
                    return@setOnNavigationItemSelectedListener true

                }
                R.id.nav_graph_dashboard -> {
                    viewPager2.setCurrentItem(1, false)
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.nav_graph_notification -> {
                    viewPager2.setCurrentItem(2, false)
                    return@setOnNavigationItemSelectedListener true
                }
            }

            false

        }

        navControllerViewModel.currentNavController.observe(this, Observer { it ->

            it?.let { event: Event<NavController> ->
                event.getContentIfNotHandled()?.let { navController ->
                    val appBarConfig = AppBarConfiguration(navController.graph)
                    dataBinding.toolbar.setupWithNavController(navController, appBarConfig)
                }
            }
        })

    }
}