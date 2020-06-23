package com.smarttoolfactory.tutorial0_1viewpager2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.smarttoolfactory.tutorial0_1viewpager2.adapter.ChildFragmentStateAdapter
import com.smarttoolfactory.tutorial0_1viewpager2.databinding.ActivityMainBinding

// TODO This tutorial is NOT finished yet
class MainActivity : AppCompatActivity() {

    private lateinit var dataBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)

        dataBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)
        dataBinding.toolbar.title = "ViewPager 2 with Nested Fragments"


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

    }

    /**
     * Sets different type of Adapters
     * *[ChildFragmentStateAdapter] is with fixed size and basic adapter to be used with fragments
     */
    private fun setViewPagerAdapter(viewPager: ViewPager2) {
        dataBinding.viewPager.adapter = ChildFragmentStateAdapter(this)
    }


    override fun onPause() {
        super.onPause()

        println("MainActivity onPause()")
    }

    override fun onStop() {
        super.onStop()
        println("MainActivity onStop()")

    }

    override fun onDestroy() {
        super.onDestroy()
        println("MainActivity onDestroy()")

    }

}