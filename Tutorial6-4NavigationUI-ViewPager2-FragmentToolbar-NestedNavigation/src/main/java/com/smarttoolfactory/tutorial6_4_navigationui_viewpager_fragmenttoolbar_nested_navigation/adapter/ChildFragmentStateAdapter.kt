package com.smarttoolfactory.tutorial6_4_navigationui_viewpager_fragmenttoolbar_nested_navigation.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.smarttoolfactory.tutorial6_4_navigationui_viewpager_fragmenttoolbar_nested_navigation.navhost.DashBoardNavHostFragment
import com.smarttoolfactory.tutorial6_4_navigationui_viewpager_fragmenttoolbar_nested_navigation.navhost.HomeNavHostFragment
import com.smarttoolfactory.tutorial6_4_navigationui_viewpager_fragmenttoolbar_nested_navigation.navhost.NotificationHostFragment


class ChildFragmentStateAdapter(fragment: Fragment) :
    FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {

        println("ChildFragmentStateAdapter createFragment() position: $position")

        return when (position) {
            0 -> HomeNavHostFragment()
            1 -> DashBoardNavHostFragment()
            else -> NotificationHostFragment()
        }
    }

}