package com.smarttoolfactory.tutorial7_2bnv_viewpager2_complexarchitecture.fragment.viewpagerfragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.smarttoolfactory.tutorial7_2bnv_viewpager2_complexarchitecture.R
import com.smarttoolfactory.tutorial7_2bnv_viewpager2_complexarchitecture.adapter.ChildFragmentStateAdapter
import com.smarttoolfactory.tutorial7_2bnv_viewpager2_complexarchitecture.databinding.FragmentViewpagerContainerBinding
import com.smarttoolfactory.tutorial7_2bnv_viewpager2_complexarchitecture.fragment.blankfragment.BaseDataBindingFragment
import com.smarttoolfactory.tutorial7_2bnv_viewpager2_complexarchitecture.viewmodel.AppbarViewModel


/**
 * Fragment that contains [ViewPager2] and [TabLayout]. If this fragments get replaced and [Fragment.onDestroyView]
 * is called there are things to be considered
 *
 * * [FragmentStateAdapter] that is not null after [Fragment.onDestroy] cause memory leak, so assign null to it
 *
 * * [TabLayoutMediator] cause memory leak if not detached after [Fragment.onDestroy] of this fragment is called.
 *
 * * Data-binding which is not null after [Fragment.onDestroy]  causes memory leak
 *
 * *[AppbarViewModel] that has a [NavController] that belong to a NavHostFragment that is to be destroyed
 * also causes memory leak.
 */
class ViewPagerContainerFragment : BaseDataBindingFragment<FragmentViewpagerContainerBinding>() {

    override fun getLayoutRes(): Int = R.layout.fragment_viewpager_container

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // ViewPager2
        val viewPager = dataBinding!!.viewPager

        /*
            Set Adapter for ViewPager inside this fragment using this Fragment,
            more specifically childFragmentManager as param
         */
        viewPager.adapter = ChildFragmentStateAdapter(childFragmentManager, viewLifecycleOwner.lifecycle)

        // TabLayout
        val tabLayout = dataBinding!!.tabLayout

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
}