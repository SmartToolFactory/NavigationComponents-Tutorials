package com.smarttoolfactory.tutorial6_0navigationui_viewpager2.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.smarttoolfactory.tutorial6_0navigationui_viewpager2.blankfragment.DashboardFragment1
import com.smarttoolfactory.tutorial6_0navigationui_viewpager2.blankfragment.HomeFragment1
import com.smarttoolfactory.tutorial6_0navigationui_viewpager2.blankfragment.NotificationFragment1


/**
 * FragmentStateAdapter to contain ViewPager2 fragments inside another fragment.
 *
 * * 🔥 Create FragmentStateAdapter with viewLifeCycleOwner instead of Fragment to make sure
 * that it lives between [Fragment.onCreateView] and [Fragment.onDestroyView] while [View] is alive
 *
 * * https://stackoverflow.com/questions/61779776/leak-canary-detects-memory-leaks-for-tablayout-with-viewpager2
 */
class ChildFragmentStateAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {

        return when (position) {
            0 -> HomeFragment1()
            1 -> DashboardFragment1()
            else -> NotificationFragment1()
        }
    }

}