package com.smarttoolfactory.tutorial8_2dynamicfeatures_complexarchitecture.fragment.viewpagerfragment

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.tabs.TabLayoutMediator
import com.smarttoolfactory.tutorial8_2dynamicfeatures_complexarchitecture.R
import com.smarttoolfactory.tutorial8_2dynamicfeatures_complexarchitecture.adapter.ChildFragmentStateAdapter
import com.smarttoolfactory.tutorial8_2dynamicfeatures_complexarchitecture.databinding.FragmentViewpagerContainerBinding
import com.smarttoolfactory.tutorial8_2dynamicfeatures_complexarchitecture.fragment.blankfragment.BaseDataBindingFragment
import com.smarttoolfactory.tutorial8_2dynamicfeatures_complexarchitecture.util.Event
import com.smarttoolfactory.tutorial8_2dynamicfeatures_complexarchitecture.viewmodel.NavControllerViewModel


class ViewPagerContainerFragment : BaseDataBindingFragment<FragmentViewpagerContainerBinding>() {

    override fun getLayoutRes(): Int = R.layout.fragment_viewpager_container

    private val navControllerViewModel by activityViewModels<NavControllerViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        println("🔥 ViewPagerContainerFragment navController: ${findNavController()}")

        setUpViewPager2AndTabLayout()

        setUpToolbar()

        subscribeAppbarNavigation()
    }

    private fun setUpViewPager2AndTabLayout() {

        val binding = dataBinding!!

        // ViewPager2
        val viewPager = binding.viewPager

        /*
            Set Adapter for ViewPager inside this fragment using this Fragment,
            more specifically childFragmentManager as param
         */
        viewPager.adapter = ChildFragmentStateAdapter(this)

        // TabLayout
        val tabLayout = binding.tabLayout

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
    }

    private fun setUpToolbar() {

        val binding = dataBinding!!

        // Set toolbar menu and item click listener
        binding.toolbar.inflateMenu(R.menu.menu_main)

        binding.toolbar.setOnMenuItemClickListener {
            onOptionsItemSelected(it)
        }
    }

    private fun subscribeAppbarNavigation() {
        navControllerViewModel.currentNavController.observe(viewLifecycleOwner, Observer { it ->

            it?.let { event: Event<NavController> ->
                event.getContentIfNotHandled()?.let { navController ->
                    val appBarConfig = AppBarConfiguration(navController.graph)
                    dataBinding?.toolbar?.setupWithNavController(navController, appBarConfig)
                }
            }
        })
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        /*
            🔥 This is the main nav controller of Activity so Camera Fragment covers the screen
             nav_graph_main's  <include-dynamic android:id="@+id/nav_graph_camera"
            is called, and whole screen is covered
         */
//        val navController = requireActivity().findNavController(R.id.main_nav_host_fragment)

        /*
            🔥 This is the nav controller of NavHostFragment of parent of this fragment
            nav_graph_view_pager's     <include-dynamic  android:id="@+id/nav_graph_camera"
            is called and only ViewPager2 area is covered
        */
        val navController = findNavController()

        // Navigates to destination which is both has the same id in menu.xml and nav_graph.xml.
        return item.onNavDestinationSelected(navController)
                || super.onOptionsItemSelected(item)
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