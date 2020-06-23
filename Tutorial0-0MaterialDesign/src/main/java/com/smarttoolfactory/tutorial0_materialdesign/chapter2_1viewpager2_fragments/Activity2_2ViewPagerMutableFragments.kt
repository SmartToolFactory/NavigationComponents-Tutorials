package com.smarttoolfactory.tutorial0_materialdesign.chapter2_1viewpager2_fragments

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.smarttoolfactory.tutorial0_materialdesign.R
import com.smarttoolfactory.tutorial0_materialdesign.blankfragment.GenericFragmentWithArgs
import com.smarttoolfactory.tutorial0_materialdesign.chapter2_1viewpager2_fragments.adapter.fragment_state_adapter.DynamicFragmentStateAdapter
import com.smarttoolfactory.tutorial0_materialdesign.databinding.Activity22viewpagerMutableFragmentsBinding
import com.smarttoolfactory.tutorial0_materialdesign.fragment_factory.GenericFragmentFactory


class Activity2_2ViewPagerMutableFragments : AppCompatActivity() {

    private lateinit var dataBinding: Activity22viewpagerMutableFragmentsBinding

    /**
     * This one is not suggested to be used
     */
//    private val dynamicINCORRECTFragmentStateAdapter: DynamicINCORRECTFragmentStateAdapter by lazy {
//        DynamicINCORRECTFragmentStateAdapter(supportFragmentManager, lifecycle)
//    }

    /**
     * Adapter that instantiates it's own fragments
     */
    private val dynamicFragmentStateAdapter: DynamicFragmentStateAdapter by lazy {
        DynamicFragmentStateAdapter(this)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        /*
            ⚠️ FragmentFactory should be placed before super.onCreate to be able
            find non-empty constructors of fragments
         */
        supportFragmentManager.fragmentFactory = GenericFragmentFactory.getFragmentFactory()

        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(
            this,
            R.layout.activity2_2viewpager_mutable_fragments
        )
        dataBinding.toolbar.title = "Add or Remove Pages"

        // TabLayout
        val tabLayout = dataBinding.tabLayout
        // ViewPager2
        val viewPager = dataBinding.viewPager

        // 🔥 Add different type of adapters
        setViewPagerAdapter(viewPager)

        // Bind tabs and viewpager
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = "Tab $position"
        }.attach()

        /*
            Add fragment, ⚠️ This is not the correct way to add fragment
            do no instantiate fragments, let adapter's createFragment do it
         */
        dataBinding.btnAddPage.setOnClickListener {
            addFragmentToViewPager()
        }

        dataBinding.btnRemovePage.setOnClickListener {
            removeFragmentFromViewPager()
        }
    }

    private fun removeFragmentFromViewPager() {

        // ❌ Don't use this one
//        dynamicINCORRECTFragmentStateAdapter.removeFragment()

        // ✅ Use this one
        dynamicFragmentStateAdapter.removeFragment()

    }

    private fun addFragmentToViewPager() {

        // ❌ Don't use this one
//        dynamicINCORRECTFragmentStateAdapter.addFragment(
//            GenericFragment.newInstance(
//                dynamicINCORRECTFragmentStateAdapter.getFragmentCount()
//            )
//        )

        // ✅ Use this one
        dynamicFragmentStateAdapter.addFragment<GenericFragmentWithArgs>()
    }

    private fun setViewPagerAdapter(viewPager: ViewPager2) {

        // ❌ Don't use this one
//        viewPager.adapter = dynamicINCORRECTFragmentStateAdapter

        // ✅ Use this one
        viewPager.adapter = dynamicFragmentStateAdapter
    }

}