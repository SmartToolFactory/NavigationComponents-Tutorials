package com.smarttoolfactory.tutorial6_7navigationui_memoryleakcheck.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.smarttoolfactory.tutorial6_7navigationui_memoryleakcheck.blankfragment.DashboardFragment1
import com.smarttoolfactory.tutorial6_7navigationui_memoryleakcheck.blankfragment.HomeFragment1
import com.smarttoolfactory.tutorial6_7navigationui_memoryleakcheck.blankfragment.LoginFragment1
import com.smarttoolfactory.tutorial6_7navigationui_memoryleakcheck.blankfragment.NotificationFragment1


class ChildFragmentStateAdapter(fragment: Fragment) :
    FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 4

    override fun createFragment(position: Int): Fragment {

        return when (position) {
            0 -> HomeFragment1()
            1 -> DashboardFragment1()
            2 -> NotificationFragment1()
            else -> LoginFragment1()
        }
    }

}