package com.smarttoolfactory.tutorial6_2navigationui_viewpager2_nestednavhost.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.smarttoolfactory.tutorial6_2navigationui_viewpager2_nestednavhost.navhost.DashBoardNavHostFragment
import com.smarttoolfactory.tutorial6_2navigationui_viewpager2_nestednavhost.navhost.HomeNavHostFragment

/**
 *  Passing [Fragment] as parameter let's this adapter to use childFragmentManager
 */
class ChildFragmentStateAdapter(private val fragment: Fragment) :
    FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {

        return when (position) {
            0 -> HomeNavHostFragment()
            else -> DashBoardNavHostFragment()
        }
    }

}