package com.smarttoolfactory.tutorial6_4_navigationui_viewpager_fragmenttoolbar.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.smarttoolfactory.tutorial6_4_navigationui_viewpager_fragmenttoolbar.navhost.DashBoardNavHostFragment
import com.smarttoolfactory.tutorial6_4_navigationui_viewpager_fragmenttoolbar.navhost.HomeNavHostFragment
import com.smarttoolfactory.tutorial6_4_navigationui_viewpager_fragmenttoolbar.navhost.NotificationHostFragment


class ChildFragmentStateAdapter(private val fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {


    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {

        return when (position) {
            0 -> HomeNavHostFragment()
            1 -> DashBoardNavHostFragment()
            else -> NotificationHostFragment()
        }
    }


}