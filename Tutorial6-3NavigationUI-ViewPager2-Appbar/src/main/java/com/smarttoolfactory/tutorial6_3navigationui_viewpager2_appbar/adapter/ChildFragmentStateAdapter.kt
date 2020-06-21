package com.smarttoolfactory.tutorial6_3navigationui_viewpager2_appbar.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavController
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.smarttoolfactory.tutorial6_3navigationui_viewpager2_appbar.blankfragment.LoginFragment1
import com.smarttoolfactory.tutorial6_3navigationui_viewpager2_appbar.navhost.DashBoardNavHostFragment
import com.smarttoolfactory.tutorial6_3navigationui_viewpager2_appbar.navhost.HomeNavHostFragment
import com.smarttoolfactory.tutorial6_3navigationui_viewpager2_appbar.navhost.NotificationHostFragment


class ChildFragmentStateAdapter(private val fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {

    val navControllerList = ArrayList<NavController?>(3).apply {
    }


    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {

        println("ChildFragmentStateAdapter createFragment() position: $position")

        return when (position) {
            0 -> HomeNavHostFragment().apply { navControllerList.add(navController) }
            1 -> DashBoardNavHostFragment().apply { navControllerList.add(navController) }
            2 -> NotificationHostFragment().apply { navControllerList.add(navController) }
            else -> LoginFragment1()
        }
    }

}