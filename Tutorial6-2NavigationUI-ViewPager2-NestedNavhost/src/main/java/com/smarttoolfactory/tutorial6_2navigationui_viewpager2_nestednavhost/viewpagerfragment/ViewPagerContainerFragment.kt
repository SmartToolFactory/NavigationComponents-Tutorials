package com.smarttoolfactory.tutorial6_2navigationui_viewpager2_nestednavhost.viewpagerfragment


import android.os.Bundle
import android.view.View
import com.google.android.material.tabs.TabLayoutMediator
import com.smarttoolfactory.tutorial6_2naigationui_viewpager2_nestednavhost.R
import com.smarttoolfactory.tutorial6_2naigationui_viewpager2_nestednavhost.databinding.FragmentViewpagerContainerBinding
import com.smarttoolfactory.tutorial6_2navigationui_viewpager2_nestednavhost.adapter.ChildFragmentStateAdapter
import com.smarttoolfactory.tutorial6_2navigationui_viewpager2_nestednavhost.blankfragment.BaseDataBindingFragment


class ViewPagerContainerFragment : BaseDataBindingFragment<FragmentViewpagerContainerBinding>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // TabLayout
        val tabLayout = dataBinding!!.tabLayout
        // ViewPager2
        val viewPager = dataBinding!!.viewPager

        /*
            🔥 Set Adapter for ViewPager inside this fragment using this Fragment,
            more specifically childFragmentManager as param
         */
        viewPager.adapter =
            ChildFragmentStateAdapter(childFragmentManager, viewLifecycleOwner.lifecycle)

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

        val viewPager2 = dataBinding?.viewPager

        /*
            Without setting ViewPager2 Adapter it causes memory leak

            https://stackoverflow.com/questions/62851425/viewpager2-inside-a-fragment-leaks-after-replacing-the-fragment-its-in-by-navig
         */
        viewPager2?.let {
            it.adapter = null
        }

        super.onDestroyView()
    }

    override fun getLayoutRes(): Int = R.layout.fragment_viewpager_container


}
