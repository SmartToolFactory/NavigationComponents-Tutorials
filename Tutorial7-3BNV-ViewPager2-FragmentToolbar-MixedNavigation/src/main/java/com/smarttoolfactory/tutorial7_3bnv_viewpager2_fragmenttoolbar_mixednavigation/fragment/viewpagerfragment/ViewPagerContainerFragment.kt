package com.smarttoolfactory.tutorial7_3bnv_viewpager2_fragmenttoolbar_mixednavigation.fragment.viewpagerfragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.google.android.material.tabs.TabLayoutMediator
import com.smarttoolfactory.tutorial7_3bnv_viewpager2_fragmenttoolbar_mixednavigation.adapter.ChildFragmentStateAdapter
import com.smarttoolfactory.tutorial7_3bnv_viewpager2_fragmenttoolbar_mixednavigation.R
import com.smarttoolfactory.tutorial7_3bnv_viewpager2_fragmenttoolbar_mixednavigation.fragment.blankfragment.BaseDataBindingFragment
import com.smarttoolfactory.tutorial7_3bnv_viewpager2_fragmenttoolbar_mixednavigation.databinding.FragmentViewpagerContainerBinding
import com.smarttoolfactory.tutorial7_3bnv_viewpager2_fragmenttoolbar_mixednavigation.viewmodel.NavControllerViewModel


class ViewPagerContainerFragment : BaseDataBindingFragment<FragmentViewpagerContainerBinding>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // ViewPager2
        val viewPager = dataBinding.viewPager

        /*
            Set Adapter for ViewPager inside this fragment using this Fragment,
            more specifically childFragmentManager as param
         */
        viewPager.adapter = ChildFragmentStateAdapter(this)

        // TabLayout
        val tabLayout = dataBinding.tabLayout

        // Bind tabs and viewpager
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = "Vertical"
                1 -> tab.text = "Horizontal"
                2 -> tab.text = "Grid"
                3 -> tab.text = "Staggered"
                4-> tab.text = "Notification"
                else -> tab.text = "Login"
            }
        }.attach()


    }

    override fun getLayoutRes(): Int = R.layout.fragment_viewpager_container
}