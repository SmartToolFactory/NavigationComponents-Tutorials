package com.smarttoolfactory.tutorial8_2dynamicfeatures_complexarchitecture.fragment.main

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.navigation.fragment.findNavController
import com.smarttoolfactory.tutorial8_2dynamicfeatures_complexarchitecture.R
import com.smarttoolfactory.tutorial8_2dynamicfeatures_complexarchitecture.adapter.BottomNavigationStateAdapter
import com.smarttoolfactory.tutorial8_2dynamicfeatures_complexarchitecture.databinding.FragmentMainWithViewpagerBinding
import com.smarttoolfactory.tutorial8_2dynamicfeatures_complexarchitecture.fragment.blankfragment.BaseDataBindingFragment


class MainFragmentWithViewPager : BaseDataBindingFragment<FragmentMainWithViewpagerBinding>() {

    override fun getLayoutRes(): Int = R.layout.fragment_main_with_viewpager


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        println("🔥 MainFragmentWithViewPager navController: ${findNavController()}")


        val viewPager2 = dataBinding.viewPager
        val bottomNavigationView = dataBinding.bottomNav

        // Cancel ViewPager swipe
        viewPager2.isUserInputEnabled = false

        // Set viewpager adapter
        viewPager2.adapter = BottomNavigationStateAdapter(this)

        // Listen bottom navigation tabs change
        bottomNavigationView.setOnNavigationItemSelectedListener {

            when (it.itemId) {
                R.id.nav_graph_view_pager -> {
                    viewPager2.setCurrentItem(0, false)
                    return@setOnNavigationItemSelectedListener true

                }
                R.id.nav_graph_dashboard -> {
                    viewPager2.setCurrentItem(1, false)
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.nav_graph_notification -> {
                    viewPager2.setCurrentItem(2, false)
                    return@setOnNavigationItemSelectedListener true
                }
            }

            false

        }

    }
}
