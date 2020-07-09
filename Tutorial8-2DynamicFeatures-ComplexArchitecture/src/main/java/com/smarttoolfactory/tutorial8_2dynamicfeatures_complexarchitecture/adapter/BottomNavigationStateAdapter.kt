package com.smarttoolfactory.tutorial8_2dynamicfeatures_complexarchitecture.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.commitNow
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter.FragmentTransactionCallback.OnPostEventListener
import com.smarttoolfactory.tutorial8_2dynamicfeatures_complexarchitecture.fragment.factory.NavHostFragmentFactory
import com.smarttoolfactory.tutorial8_2dynamicfeatures_complexarchitecture.fragment.viewpagerfragment.DashboardNavHostFragment
import com.smarttoolfactory.tutorial8_2dynamicfeatures_complexarchitecture.fragment.viewpagerfragment.NotificationHostFragment
import com.smarttoolfactory.tutorial8_2dynamicfeatures_complexarchitecture.fragment.viewpagerfragment.ViewPagerContainerFragment


class BottomNavigationStateAdapter(private val fragment: Fragment) :
    FragmentStateAdapter(fragment) {


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


    override fun createFragment(position: Int): Fragment {

        return when (position) {
            0 -> ViewPagerContainerFragment()
            1 -> DashboardNavHostFragment()
            else -> NotificationHostFragment()

        }
    }

}