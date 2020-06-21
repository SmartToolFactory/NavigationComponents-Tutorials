package com.smarttoolfactory.tutorial6_2navigationui_viewpager2_nestednavhost.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.smarttoolfactory.tutorial6_2navigationui_viewpager2_nestednavhost.blankfragment.CameraFragment
import com.smarttoolfactory.tutorial6_2navigationui_viewpager2_nestednavhost.navhost.DashBoardNavHostFragment
import com.smarttoolfactory.tutorial6_2navigationui_viewpager2_nestednavhost.navhost.HomeNavHostFragment
import com.smarttoolfactory.tutorial6_2navigationui_viewpager2_nestednavhost.navhost.NotificationHostFragment

/**
 *  Passing [Fragment] as parameter let's this adapter to use childFragmentManager
 */
class ChildFragmentStateAdapter(private val fragment: Fragment) :
    FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {

        return when (position) {
            0 -> HomeNavHostFragment()
            1 -> DashBoardNavHostFragment()
            2 -> NotificationHostFragment()
            else -> CameraFragment()
        }
    }

}