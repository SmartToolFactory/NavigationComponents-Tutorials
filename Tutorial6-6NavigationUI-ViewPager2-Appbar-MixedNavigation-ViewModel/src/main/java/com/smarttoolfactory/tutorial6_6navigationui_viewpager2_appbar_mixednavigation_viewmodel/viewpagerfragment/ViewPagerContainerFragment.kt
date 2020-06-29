package com.smarttoolfactory.tutorial6_6navigationui_viewpager2_appbar_mixednavigation_viewmodel.viewpagerfragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.smarttoolfactory.tutorial6_6navigationui_viewpager2_appbar_mixednavigation_viewmodel.adapter.ChildFragmentStateAdapter
import com.smarttoolfactory.tutorial6_6navigationui_viewpager2_appbar_mixednavigation_viewmodel.R
import com.smarttoolfactory.tutorial6_6navigationui_viewpager2_appbar_mixednavigation_viewmodel.blankfragment.BaseDataBindingFragment
import com.smarttoolfactory.tutorial6_6navigationui_viewpager2_appbar_mixednavigation_viewmodel.databinding.FragmentViewpagerContainerBinding
import com.smarttoolfactory.tutorial6_6navigationui_viewpager2_appbar_mixednavigation_viewmodel.viewmodel.AppbarViewModel


class ViewPagerContainerFragment : BaseDataBindingFragment<FragmentViewpagerContainerBinding>() {

    val appbarViewModel by activityViewModels<AppbarViewModel>()

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
                0 -> tab.text = "Home"
                1 -> tab.text = "Dashboard"
                2 -> tab.text = "Notification"
                3 -> tab.text = "Login"
            }
        }.attach()


        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {

            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                appbarViewModel.currentNavController.value =
                    appbarViewModel.currentNavController.value

                if (position == 3) {

                }

            }
        })

    }

    override fun getLayoutRes(): Int = R.layout.fragment_viewpager_container


}