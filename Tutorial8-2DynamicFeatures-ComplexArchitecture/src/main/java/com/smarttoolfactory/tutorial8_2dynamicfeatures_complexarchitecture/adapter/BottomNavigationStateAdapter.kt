package com.smarttoolfactory.tutorial8_2dynamicfeatures_complexarchitecture.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commitNow
import androidx.lifecycle.Lifecycle
import androidx.navigation.dynamicfeatures.fragment.DynamicNavHostFragment
import androidx.navigation.fragment.NavHostFragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter.FragmentTransactionCallback.OnPostEventListener
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.smarttoolfactory.tutorial8_2dynamicfeatures_complexarchitecture.R
import com.smarttoolfactory.tutorial8_2dynamicfeatures_complexarchitecture.fragment.navhost.NavHostContainerFragment
import com.smarttoolfactory.tutorial8_2dynamicfeatures_complexarchitecture.fragment.viewpagerfragment.ViewPagerContainerFragment


/**
 * ViewPager2 Adapter for changing tabs of BottomNavigationView
 *
 * * This adapter is used because [BottomNavigationView] with back navigation does not support
 *  [DynamicNavHostFragment] since [NavHostFragment.create] returns [NavHostFragment]
 * instead of type T:[NavHostFragment]`
 */
class BottomNavigationStateAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {


    init {

        // Add a FragmentTransactionCallback to handle changing
        // the primary navigation fragment
        registerFragmentTransactionCallback(object : FragmentTransactionCallback() {

            override fun onFragmentMaxLifecyclePreUpdated(
                fragment: Fragment,
                maxLifecycleState: Lifecycle.State
            ) = if (maxLifecycleState == Lifecycle.State.RESUMED) {

                // This fragment is becoming the active Fragment - set it to
                // the primary navigation fragment in the OnPostEventListener
                OnPostEventListener {
                    fragment.parentFragmentManager.commitNow {
                        setPrimaryNavigationFragment(fragment)
                    }
                }

            } else {
                super.onFragmentMaxLifecyclePreUpdated(fragment, maxLifecycleState)
            }
        })
    }

    override fun getItemCount(): Int = 3


//    override fun createFragment(position: Int): Fragment {
//
//        return when (position) {
//            0 -> ViewPagerContainerFragment()
//            1 -> DashboardNavHostFragment()
//            else -> NotificationHostFragment()
//
//        }
//    }

    override fun createFragment(position: Int): Fragment {

        return when (position) {

            // ViewPager Fragment NavHost Container
            0 -> NavHostContainerFragment.newInstance(
                R.layout.fragment_navhost_viewpager_container,
                R.id.nested_nav_host_fragment_view_pager_container
            )

            // Dashboard Fragment NavHost Container
            1 -> NavHostContainerFragment.newInstance(
                R.layout.fragment_navhost_dashboard,
                R.id.nested_nav_host_fragment_dashboard
            )

            // Notification Fragment NavHost Container
            else -> NavHostContainerFragment.newInstance(
                R.layout.fragment_navhost_notification,
                R.id.nested_nav_host_fragment_notification
            )

        }
    }

}