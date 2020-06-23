package com.smarttoolfactory.tutorial0_materialdesign.blankfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.tabs.TabLayoutMediator
import com.smarttoolfactory.tutorial0_materialdesign.R
import com.smarttoolfactory.tutorial0_materialdesign.chapter2_1viewpager2_fragments.Activity2_3ViewPagerInsideFragment
import com.smarttoolfactory.tutorial0_materialdesign.chapter2_1viewpager2_fragments.adapter.fragment_state_adapter.ChildFragmentStateAdapter
import com.smarttoolfactory.tutorial0_materialdesign.databinding.FragmentWithViewpagerBinding


/**
 * Fragment that contains child supportFragmentManager to navigate to
 * child fragment with button click. This fragment is used inside [Activity2_3ViewPagerInsideFragment]
 */
class FragmentThatContainsViewPager : BaseDataBindingFragment<FragmentWithViewpagerBinding>() {

    override fun getLayoutRes(): Int {
        return R.layout.fragment_with_viewpager
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        println("🥶 ${this.javaClass.simpleName} onCreateView() this: $this")
        return super.onCreateView(inflater, container, savedInstanceState)
    }

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
            tab.text = "Tab $position"
        }.attach()

    }

}
