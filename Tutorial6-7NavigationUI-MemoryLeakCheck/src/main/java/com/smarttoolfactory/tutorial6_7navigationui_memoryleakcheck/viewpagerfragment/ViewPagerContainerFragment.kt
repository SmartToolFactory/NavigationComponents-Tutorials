package com.smarttoolfactory.tutorial6_7navigationui_memoryleakcheck.viewpagerfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.smarttoolfactory.tutorial6_7navigationui_memoryleakcheck.R
import com.smarttoolfactory.tutorial6_7navigationui_memoryleakcheck.adapter.ChildFragmentStateAdapter


class ViewPagerContainerFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_viewpager_container, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // ViewPager2
        val viewPager = view.findViewById<ViewPager2>(R.id.viewPager)

        /*
            Set Adapter for ViewPager inside this fragment using this Fragment,
            more specifically childFragmentManager as param
         */
        viewPager.adapter = ChildFragmentStateAdapter(this)

        // TabLayout
        val tabLayout = view.findViewById<TabLayout>(R.id.tabLayout)

        // Bind tabs and viewpager
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = "Home"
                1 -> tab.text = "Dashboard"
                2 -> tab.text = "Notification"
                3 -> tab.text = "Login"
            }
        }.attach()
    }

    override fun onDestroyView() {

        val viewPager2 = view?.findViewById<ViewPager2>(R.id.viewPager)

        viewPager2?.let {
            it.adapter = null
        }

        super.onDestroyView()

    }

}