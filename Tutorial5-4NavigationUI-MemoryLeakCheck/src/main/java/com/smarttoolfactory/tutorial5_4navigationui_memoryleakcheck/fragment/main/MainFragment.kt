package com.smarttoolfactory.tutorial5_4navigationui_memoryleakcheck.fragment.main

import android.os.Bundle
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.smarttoolfactory.tutorial5_4navigationui_memoryleakcheck.R
import com.smarttoolfactory.tutorial5_4navigationui_memoryleakcheck.databinding.FragmentMainBinding
import com.smarttoolfactory.tutorial5_4navigationui_memoryleakcheck.fragment.blankfragment.BaseDataBindingFragment
import com.smarttoolfactory.tutorial5_4navigationui_memoryleakcheck.setupWithNavController


class MainFragment : BaseDataBindingFragment<FragmentMainBinding>() {
    override fun getLayoutRes(): Int = R.layout.fragment_main

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (savedInstanceState == null) {
            setupBottomNavigationBar()
        } // Else, need to wait for onRestoreInstanceState
    }


    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        // Now that BottomNavigationBar has restored its instance state
        // and its selectedItemId, we can proceed with setting up the
        // BottomNavigationBar with Navigation
        setupBottomNavigationBar()
    }

    /**
     * Called on first creation and when restoring state.
     */
    private fun setupBottomNavigationBar() {

        val bottomNavigationView = dataBinding!!.bottomNav

        val navGraphIds = listOf(
            R.navigation.nav_graph_home,
            R.navigation.nav_graph_dashboard,
            R.navigation.nav_graph_notification,
            R.navigation.nav_graph_login
        )

        // Setup the bottom navigation view with a list of navigation graphs
        val controller = bottomNavigationView.setupWithNavController(
            navGraphIds = navGraphIds,
            fragmentManager = childFragmentManager,
            containerId = R.id.nav_host_container,
            intent = requireActivity().intent
        )

        // Whenever the selected controller changes, setup the action bar.
        subscribeBottomNavigation(controller)

        addNotificationBadge()

    }

    private fun subscribeBottomNavigation(controller: LiveData<NavController>) {
        controller.observe(viewLifecycleOwner, Observer { navController ->
            val appBarConfig = AppBarConfiguration(navController.graph)
            dataBinding!!.toolbar.setupWithNavController(navController, appBarConfig)
        })
    }


    private fun addNotificationBadge() {
        // Add badge to bottom navigation
        val bottomNavigationView = dataBinding!!.bottomNav
        val menuItemId = bottomNavigationView.menu.getItem(2).itemId
        val badge = bottomNavigationView.getOrCreateBadge(menuItemId)
        badge.number = 2
    }
}

