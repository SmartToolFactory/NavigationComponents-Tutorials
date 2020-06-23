package com.smarttoolfactory.tutorial6_3navigationui_viewpager2_appbar_nested_navigation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.smarttoolfactory.tutorial6_3navigationui_viewpager2_appbar.R
import com.smarttoolfactory.tutorial6_3navigationui_viewpager2_appbar.databinding.ActivityMainBinding
import com.smarttoolfactory.tutorial6_3navigationui_viewpager2_appbar_nested_navigation.adapter.ChildFragmentStateAdapter

/**
 * MainActivity has it's appbar that navigation is controlled using the [NavController]
 * retrieved from [NavHostFragment] via [LiveData]
 *
 * * Issue with rotation, when device rotated [ChildFragmentStateAdapter.createFragment] method
 *   is not called and it's not possible to access [NavController] of newly created fragments
 *
 */
class MainActivity : AppCompatActivity(), Observer<NavController> {

    private lateinit var appBarConfiguration: AppBarConfiguration

    private var currentNavController: NavController? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val dataBinding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)

        // ViewPager2
        val viewPager = dataBinding.viewPager

        /*
            🔥 Set Adapter for ViewPager inside this fragment using this Fragment,
            more specifically childFragmentManager as param
         */

        val adapter = ChildFragmentStateAdapter(this)
        viewPager.adapter = adapter

        // TabLayout
        val tabLayout = dataBinding.tabLayout


        // Bind tabs and viewpager
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            when(position) {
                0->  tab.text = "Home"
                1->  tab.text = "Dashboard"
                2->  tab.text = "Notification"
            }
        }.attach()

        // Set support action bar
        setSupportActionBar(dataBinding.toolbar)

        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)

                setAppbarNavigation(adapter, position)
            }
        })

    }

    private fun setAppbarNavigation(
        adapter: ChildFragmentStateAdapter,
        position: Int
    ) {
        try {

            adapter.navControllerMap?.values.forEach {
                it.removeObserver(this@MainActivity)
            }

            adapter.navControllerMap[position]?.observe(
                this@MainActivity,
                this@MainActivity
            )

        } catch (e: Exception) {
            e.printStackTrace()
        }
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

    override fun onChanged(navController: NavController?) {
        currentNavController = navController
        setAppbarNavigation()
    }

}