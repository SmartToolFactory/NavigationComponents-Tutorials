package com.smarttoolfactory.tutorial6_2navigationui_viewpager2_nestednavhost.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.smarttoolfactory.tutorial6_2navigationui_viewpager2_nestednavhost.blankfragment.LoginFragment1
import com.smarttoolfactory.tutorial6_2navigationui_viewpager2_nestednavhost.navhost.DashboardNavHostFragment
import com.smarttoolfactory.tutorial6_2navigationui_viewpager2_nestednavhost.navhost.HomeNavHostFragment
import com.smarttoolfactory.tutorial6_2navigationui_viewpager2_nestednavhost.navhost.NotificationHostFragment

/**
 *  Passing [Fragment] as parameter let's this adapter to use childFragmentManager
 */
class ChildFragmentStateAdapter(fragment: Fragment) :
    FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 4

    override fun createFragment(position: Int): Fragment {
x3
        println("ChildFragmentStateAdapter createFragment() position: $position")

        return when (position) {
            0 -> HomeNavHostFragment()
            1 -> DashboardNavHostFragment()
            2 -> NotificationHostFragment()
            else -> LoginFragment1()
        }
    }

}