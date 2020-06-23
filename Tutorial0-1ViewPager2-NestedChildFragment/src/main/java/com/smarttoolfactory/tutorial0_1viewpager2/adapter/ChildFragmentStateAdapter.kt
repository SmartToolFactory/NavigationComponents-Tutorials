package com.smarttoolfactory.tutorial0_1viewpager2.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.smarttoolfactory.tutorial0_1viewpager2.blankfragment.DashboardHostFragment
import com.smarttoolfactory.tutorial0_1viewpager2.blankfragment.HomeHostFragment


/**
 *  Passing [Fragment] as parameter let's this adapter to use childFragmentManager
 */
class ChildFragmentStateAdapter(private val fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {

        return when (position) {
            0 -> HomeHostFragment()
            else -> DashboardHostFragment()
        }
    }

}