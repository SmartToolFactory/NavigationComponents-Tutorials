package com.smarttoolfactory.tutorial8_2dynamicfeatures_complexarchitecture.fragment.viewpagerfragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.tabs.TabLayoutMediator
import com.smarttoolfactory.tutorial8_2dynamicfeatures_complexarchitecture.R
import com.smarttoolfactory.tutorial8_2dynamicfeatures_complexarchitecture.adapter.ChildFragmentStateAdapter
import com.smarttoolfactory.tutorial8_2dynamicfeatures_complexarchitecture.databinding.FragmentViewpagerContainerBinding
import com.smarttoolfactory.tutorial8_2dynamicfeatures_complexarchitecture.fragment.blankfragment.BaseDataBindingFragment
import com.smarttoolfactory.tutorial8_2dynamicfeatures_complexarchitecture.util.Event
import com.smarttoolfactory.tutorial8_2dynamicfeatures_complexarchitecture.viewmodel.NavControllerViewModel


class ViewPagerContainerFragment : BaseDataBindingFragment<FragmentViewpagerContainerBinding>() {

    private val navControllerViewModel by activityViewModels<NavControllerViewModel>()

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
                4 -> tab.text = "Notification"
                else -> tab.text = "Login"
            }
        }.attach()


        dataBinding.toolbar.inflateMenu(R.menu.menu_main)

        subscribeAppbarNavigation()
    }

    private fun subscribeAppbarNavigation() {
        navControllerViewModel.currentNavController.observe(viewLifecycleOwner, Observer { it ->

            it?.let { event: Event<NavController> ->
                event.getContentIfNotHandled()?.let { navController ->
                    val appBarConfig = AppBarConfiguration(navController.graph)
                    dataBinding.toolbar.setupWithNavController(navController, appBarConfig)
                }
            }
        })
    }

    override fun getLayoutRes(): Int = R.layout.fragment_viewpager_container
}