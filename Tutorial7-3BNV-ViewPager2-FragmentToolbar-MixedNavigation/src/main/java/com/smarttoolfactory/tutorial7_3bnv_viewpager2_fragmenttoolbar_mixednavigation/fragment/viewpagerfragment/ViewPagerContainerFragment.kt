package com.smarttoolfactory.tutorial7_3bnv_viewpager2_fragmenttoolbar_mixednavigation.fragment.viewpagerfragment

import android.os.Bundle
import android.view.View
import com.google.android.material.tabs.TabLayoutMediator
import com.smarttoolfactory.tutorial7_3bnv_viewpager2_fragmenttoolbar_mixednavigation.R
import com.smarttoolfactory.tutorial7_3bnv_viewpager2_fragmenttoolbar_mixednavigation.adapter.ChildFragmentStateAdapter
import com.smarttoolfactory.tutorial7_3bnv_viewpager2_fragmenttoolbar_mixednavigation.databinding.FragmentViewpagerContainerBinding
import com.smarttoolfactory.tutorial7_3bnv_viewpager2_fragmenttoolbar_mixednavigation.fragment.blankfragment.BaseDataBindingFragment


class ViewPagerContainerFragment : BaseDataBindingFragment<FragmentViewpagerContainerBinding>() {

    override fun getLayoutRes(): Int = R.layout.fragment_viewpager_container

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // ViewPager2
        val viewPager = dataBinding!!.viewPager

        // TabLayout
        val tabLayout = dataBinding!!.tabLayout

        /*
            Set Adapter for ViewPager inside this fragment using this Fragment,
            more specifically childFragmentManager as param

            🔥 Create FragmentStateAdapter with viewLifeCycleOwner
            https://stackoverflow.com/questions/61779776/leak-canary-detects-memory-leaks-for-tablayout-with-viewpager2
         */
        viewPager.adapter =
            ChildFragmentStateAdapter(childFragmentManager, viewLifecycleOwner.lifecycle)


        // Bind tabs and viewpager
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = "Vertical"
                1 -> tab.text = "Horizontal"
                2 -> tab.text = "Grid"
                3 -> tab.text = "Staggered"
                4 -> tab.text = "Notification"
                else -> tab.text = "Login"
            }
        }.attach()


    }

    override fun onDestroyView() {


        // ViewPager2
        val viewPager2 = dataBinding!!.viewPager
        // TabLayout
        val tabLayout = dataBinding!!.tabLayout

        /*
              🔥 Detach TabLayoutMediator since it causing memory leaks when it's in a fragment
              https://stackoverflow.com/questions/61779776/leak-canary-detects-memory-leaks-for-tablayout-with-viewpager2
           */
        TabLayoutMediator(tabLayout, viewPager2, tabConfigurationStrategy).detach()

        /*
            🔥 Without setting ViewPager2 Adapter to null it causes memory leak
            https://stackoverflow.com/questions/62851425/viewpager2-inside-a-fragment-leaks-after-replacing-the-fragment-its-in-by-navig
         */
        viewPager2?.let {
            it.adapter = null
        }

        super.onDestroyView()

    }

    private val tabConfigurationStrategy =
        TabLayoutMediator.TabConfigurationStrategy { tab, position ->
            when (position) {
                0 -> tab.text = "Vertical"
                1 -> tab.text = "Horizontal"
                2 -> tab.text = "Grid"
                3 -> tab.text = "Staggered"
                4 -> tab.text = "Notification"
                else -> tab.text = "Login"
            }
        }
}