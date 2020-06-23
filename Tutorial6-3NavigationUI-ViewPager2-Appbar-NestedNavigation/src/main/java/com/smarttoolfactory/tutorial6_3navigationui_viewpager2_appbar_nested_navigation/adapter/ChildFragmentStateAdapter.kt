package com.smarttoolfactory.tutorial6_3navigationui_viewpager2_appbar_nested_navigation.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LiveData
import androidx.navigation.NavController
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.smarttoolfactory.tutorial6_3navigationui_viewpager2_appbar_nested_navigation.navhost.DashBoardNavHostFragment
import com.smarttoolfactory.tutorial6_3navigationui_viewpager2_appbar_nested_navigation.navhost.HomeNavHostFragment
import com.smarttoolfactory.tutorial6_3navigationui_viewpager2_appbar_nested_navigation.navhost.NotificationHostFragment


class ChildFragmentStateAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {

    val navControllerMap = HashMap<Int, LiveData<NavController>>()

    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {

        return when (position) {
            0 -> HomeNavHostFragment().apply {
                navControllerMap[position] = homeNavController
            }
            1 -> DashBoardNavHostFragment().apply {
                navControllerMap[position] = dashboardNavController
            }
            else -> NotificationHostFragment().apply {
                navControllerMap[position] = notificationNavController
            }
        }
    }
}