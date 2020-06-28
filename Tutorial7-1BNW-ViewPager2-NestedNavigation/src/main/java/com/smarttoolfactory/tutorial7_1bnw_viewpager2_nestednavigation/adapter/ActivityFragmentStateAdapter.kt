package com.smarttoolfactory.tutorial7_1bnw_viewpager2_nestednavigation.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LiveData
import androidx.navigation.NavController
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.smarttoolfactory.tutorial7_1bnw_viewpager2_nestednavigation.navhost.DashBoardNavHostFragment
import com.smarttoolfactory.tutorial7_1bnw_viewpager2_nestednavigation.navhost.HomeNavHostFragment
import com.smarttoolfactory.tutorial7_1bnw_viewpager2_nestednavigation.navhost.NotificationHostFragment


/**
 * This [FragmentStateAdapter] is used by Activity and maps [NavController] in [LiveData]
 * to position to be used for setting main Appbar nav graph when ViewPager position changes
 */
class ActivityFragmentStateAdapter(fragmentActivity: FragmentActivity) :
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