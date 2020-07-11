package com.smarttoolfactory.tutorial6_1navigationui_viewpager2.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.smarttoolfactory.tutorial6_1navigationui_viewpager2.blankfragment.DashboardFragment1
import com.smarttoolfactory.tutorial6_1navigationui_viewpager2.blankfragment.HomeFragment1
import com.smarttoolfactory.tutorial6_1navigationui_viewpager2.blankfragment.NotificationFragment1

/**
 *  Passing [Fragment] as parameter let's this adapter to use childFragmentManager
 */
class ChildFragmentStateAdapter(fragment: Fragment) :
    FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {

        return when (position) {
            0 -> HomeFragment1()
            1 -> DashboardFragment1()
            else -> NotificationFragment1()
        }
    }

}