package com.smarttoolfactory.tutorial0_materialdesign.chapter2_1viewpager2_fragments

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentFactory
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.smarttoolfactory.tutorial0_materialdesign.R
import com.smarttoolfactory.tutorial0_materialdesign.chapter2_1viewpager2_fragments.adapter.fragment_state_adapter.StaticFragmentFactoryStateAdapter
import com.smarttoolfactory.tutorial0_materialdesign.chapter2_1viewpager2_fragments.adapter.fragment_state_adapter.StaticFragmentStateAdapter
import com.smarttoolfactory.tutorial0_materialdesign.databinding.Activity21viewpagerBinding
import com.smarttoolfactory.tutorial0_materialdesign.fragment_factory.GenericFragmentFactory


/**
 * This example is to demonstrate [ViewPager2] with different type of [FragmentStateAdapter]s
 * *[StaticFragmentStateAdapter] is with fixed size and basic adapter to be used with fragments
 *
 * * [StaticFragmentFactoryStateAdapter] is adapter with [FragmentFactory]
 *
 * ### Note: [FragmentFactory] should be set before [AppCompatActivity.onCreate] to prevent crash due to not finding constructor with no arguments
 *
 */
class Activity2_1ViewPagerFragmentAdapter : AppCompatActivity() {

    lateinit var dataBinding: Activity21viewpagerBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        /*
          ⚠️ FragmentFactory should be placed before super.onCreate to be able
          find non-empty constructors of fragments
       */
        supportFragmentManager.fragmentFactory = GenericFragmentFactory.getFragmentFactory()

        super.onCreate(savedInstanceState)

        dataBinding =
            DataBindingUtil.setContentView(this, R.layout.activity2_1viewpager)
        dataBinding.toolbar.title = "Try Different Adapters"


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

        // Create a page change listener to observe page change and/or scrolls
        setOnPageChangeCallback(viewPager)

    }

    /**
     * Sets different type of Adapters
     * *[StaticFragmentStateAdapter] is with fixed size and basic adapter to be used with fragments
     *
     * * [StaticFragmentFactoryStateAdapter] is adapter with [FragmentFactory]
     */
    private fun setViewPagerAdapter(viewPager: ViewPager2) {

        // ⚠️ Uses default FragmentStateAdapter
//        viewPager.adapter = StaticFragmentStateAdapter(supportFragmentManager, lifecycle)

        // ⚠️ Uses FragmentStateAdapter with FragmentFactory
        dataBinding.viewPager.adapter = StaticFragmentFactoryStateAdapter(this)
    }

    private fun setOnPageChangeCallback(viewPager: ViewPager2) {

        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {

            override fun onPageSelected(position: Int) {
                Toast.makeText(applicationContext, "ViewPager onPageSelected() $position", Toast.LENGTH_SHORT).show()
                println("⏱ ViewPager2.OnPageChangeCallback() onPageSelected() $position")
            }
//            override fun onPageScrolled(
//                position: Int,
//                positionOffset: Float,
//                positionOffsetPixels: Int
//            ) {}
//
//            override fun onPageScrollStateChanged(state: Int) {}

        })
    }

}