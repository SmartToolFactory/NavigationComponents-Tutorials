package com.smarttoolfactory.tutorial6_3navigationui_viewpager2_appbar

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.smarttoolfactory.tutorial6_3navigationui_viewpager2_appbar.adapter.ChildFragmentStateAdapter
import com.smarttoolfactory.tutorial6_3navigationui_viewpager2_appbar.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration

    private var currentNavController: NavController? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val dataBinding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)


        // TabLayout
        val tabLayout = dataBinding.tabLayout
        // ViewPager2
        val viewPager = dataBinding.viewPager

        /*
            🔥 Set Adapter for ViewPager inside this fragment using this Fragment,
            more specifically childFragmentManager as param
         */

        val adapter = ChildFragmentStateAdapter(this)
        viewPager.adapter = adapter

        // Bind tabs and viewpager
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = "Tab $position"
        }.attach()

        // Set support action bar
        setSupportActionBar(dataBinding.toolbar)


        currentNavController = adapter.navControllerList.firstOrNull()

        viewPager.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                currentNavController = adapter.navControllerList[position]
                setAppbarNavigation()
            }
        })

    }

    private fun setAppbarNavigation() {
        
        currentNavController?.let {
            // Get App Configuration from nav graph
            appBarConfiguration = AppBarConfiguration(it.graph)

            // Handles arrow back button
            setupActionBarWithNavController(it, appBarConfiguration)
        }
    }


    // This function is required with appbar to handle back button
    override fun onSupportNavigateUp(): Boolean {
        return currentNavController?.navigateUp(appBarConfiguration) ?: false || super.onSupportNavigateUp()
    }

}