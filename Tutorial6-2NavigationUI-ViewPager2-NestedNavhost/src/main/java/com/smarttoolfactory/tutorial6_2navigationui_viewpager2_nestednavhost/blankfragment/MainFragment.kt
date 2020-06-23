package com.smarttoolfactory.tutorial6_2navigationui_viewpager2_nestednavhost.blankfragment


import android.os.Bundle
import android.view.View
import com.google.android.material.tabs.TabLayoutMediator
import com.smarttoolfactory.tutorial6_2naigationui_viewpager2_nestednavhost.R
import com.smarttoolfactory.tutorial6_2naigationui_viewpager2_nestednavhost.databinding.FragmentMainBinding
import com.smarttoolfactory.tutorial6_2navigationui_viewpager2_nestednavhost.adapter.ChildFragmentStateAdapter


class MainFragment : BaseDataBindingFragment<FragmentMainBinding>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // TabLayout
        val tabLayout = dataBinding.tabLayout
        // ViewPager2
        val viewPager = dataBinding.viewPager

        /*
            🔥 Set Adapter for ViewPager inside this fragment using this Fragment,
            more specifically childFragmentManager as param
         */
        viewPager.adapter = ChildFragmentStateAdapter(this)

        // Bind tabs and viewpager
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
           when(position) {
               0->  tab.text = "Home"
               1->  tab.text = "Notification"
               2->  tab.text = "Dashboard"
               3->  tab.text = "Login"
           }
        }.attach()

    }

    override fun getLayoutRes(): Int = R.layout.fragment_main


}
