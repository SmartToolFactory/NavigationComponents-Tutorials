package com.smarttoolfactory.tutorial7_1bnv_viewpager2_nestednavigation

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.smarttoolfactory.tutorial7_1bnv_viewpager2_nestednavigation.adapter.ActivityFragmentStateAdapter
import com.smarttoolfactory.tutorial7_1bnv_viewpager2_nestednavigation.databinding.ActivityMainBinding
import com.smarttoolfactory.tutorial7_1bnv_viewpager2_nestednavigation.viewmodel.AppbarViewModel
import com.smarttoolfactory.tutorial7_2bnv_viewpager2_complexarchitecture.util.Event


/*
   *** Navigation Architecture ***

     MainActivity(BottomNavigationView + + ViewPager2 + Appbar + Toolbar)
           |- HomeNavHostFragment
           |   |- HF1 -> HF2 -> HF3
           |
           |- DashboardNavHostFragment
           |   |- DF1 -> DF2 -> DF3
           |
           |- NotificationHostFragment
           |   |- NF1 -> NF2 -> NF3

 */
/**
 * In this example [BottomNavigationView] selects which page of [ViewPager2] should be opened using
 * [BottomNavigationView.setOnNavigationItemSelectedListener]
 *
 * Appbar title is changed using  [AppbarViewModel.currentNavController] of visible [NavHostFragment]  on screen of ViePager2 page
 */
class MainActivity : AppCompatActivity() {

//    private val appbarViewModel by viewModels<AppbarViewModel>()<AppbarViewModel>()

    private val appbarViewModel: AppbarViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val dataBinding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)

        val viewPager2 = dataBinding.viewPager
        val bottomNavigationView = dataBinding.bottomNav

        // Cancel ViewPager swipe
        viewPager2.isUserInputEnabled = false

        // Set viewpager adapter
        viewPager2.adapter = ActivityFragmentStateAdapter(this)

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

        appbarViewModel.currentNavController.observe(this, Observer { it ->

            it?.let { event: Event<NavController> ->
                event.getContentIfNotHandled()?.let { navController ->
                    val appBarConfig = AppBarConfiguration(navController.graph)
                    dataBinding.toolbar.setupWithNavController(navController, appBarConfig)
                }
            }
        })

    }
}