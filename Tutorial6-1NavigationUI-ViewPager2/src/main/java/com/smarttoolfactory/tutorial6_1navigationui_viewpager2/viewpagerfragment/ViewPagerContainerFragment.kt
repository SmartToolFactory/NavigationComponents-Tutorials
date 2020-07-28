package com.smarttoolfactory.tutorial6_1navigationui_viewpager2.viewpagerfragment


import android.os.Bundle
import android.view.View
import com.google.android.material.tabs.TabLayoutMediator
import com.smarttoolfactory.tutorial6_1navigationui_viewpager2.R
import com.smarttoolfactory.tutorial6_1navigationui_viewpager2.adapter.ChildFragmentStateAdapter
import com.smarttoolfactory.tutorial6_1navigationui_viewpager2.blankfragment.BaseDataBindingFragment
import com.smarttoolfactory.tutorial6_1navigationui_viewpager2.databinding.FragmentViewpagerContainerBinding


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
            tab.text = when (position) {
                0 -> "Home"
                1 -> "Dashboard"
                else -> "Notification"
            }
        }.attach()

    }

    override fun getLayoutRes(): Int = R.layout.fragment_viewpager_container


//    override fun onDestroyView() {
//
//        val viewPager2 = dataBinding?.viewPager
//
//        /*
//           🔥 Without setting ViewPager2 Adapter it causes memory leak if ViewPager2 is inside a Fragment
//
//            https://stackoverflow.com/questions/62851425/viewpager2-inside-a-fragment-leaks-after-replacing-the-fragment-its-in-by-navig
//         */
//        viewPager2?.let {
//            it.adapter = null
//        }
//
//        super.onDestroyView()
//    }

}
