package com.smarttoolfactory.tutorial6_0navigationui_viewpager2.viewpagerfragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.smarttoolfactory.tutorial6_0navigationui_viewpager2.R
import com.smarttoolfactory.tutorial6_0navigationui_viewpager2.adapter.ChildFragmentStateAdapter


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

        // TabLayout
        val tabLayout = view.findViewById<TabLayout>(R.id.tabLayout)
        // ViewPager2
        val viewPager = view.findViewById<ViewPager2>(R.id.viewPager)

        /*
            🔥 Set Adapter for ViewPager inside this fragment using this Fragment,
            more specifically childFragmentManager as param
         */
        viewPager.adapter =
            ChildFragmentStateAdapter(childFragmentManager, viewLifecycleOwner.lifecycle)

        // Bind tabs and viewpager
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> "Home"
                1 -> "Dashboard"
                else -> "Notification"
            }
        }.attach()

    }

}
