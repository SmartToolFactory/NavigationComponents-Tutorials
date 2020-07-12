package com.smarttoolfactory.tutorial7_3bnv_viewpager2_fragmenttoolbar_mixednavigation.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commitNow
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.smarttoolfactory.tutorial7_3bnv_viewpager2_fragmenttoolbar_mixednavigation.fragment.blankfragment.DashboardFragment1
import com.smarttoolfactory.tutorial7_3bnv_viewpager2_fragmenttoolbar_mixednavigation.fragment.blankfragment.LoginFragment1
import com.smarttoolfactory.tutorial7_3bnv_viewpager2_fragmenttoolbar_mixednavigation.fragment.blankfragment.NotificationFragment1
import com.smarttoolfactory.tutorial7_3bnv_viewpager2_fragmenttoolbar_mixednavigation.fragment.navhost.*


/**
 * FragmentStateAdapter to contain ViewPager2 fragments inside another fragment.
 *
 * * 🔥 Create FragmentStateAdapter with viewLifeCycleOwner instead of Fragment to make sure
 * that it lives between [Fragment.onCreateView] and [Fragment.onDestroyView] while [View] is alive
 *
 * * https://stackoverflow.com/questions/61779776/leak-canary-detects-memory-leaks-for-tablayout-with-viewpager2
 */
class ChildFragmentStateAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

//    private val fragmentTransactionCallback = object : FragmentTransactionCallback() {
//
//        override fun onFragmentMaxLifecyclePreUpdated(
//            fragment: Fragment,
//            maxLifecycleState: Lifecycle.State
//        ) = if (maxLifecycleState == Lifecycle.State.RESUMED) {
//
//            // This fragment is becoming the active Fragment - set it to
//            // the primary navigation fragment in the OnPostEventListener
//            OnPostEventListener {
//                fragment.parentFragmentManager.commitNow {
//                    setPrimaryNavigationFragment(fragment)
//                }
//            }
//
//        } else {
//            super.onFragmentMaxLifecyclePreUpdated(fragment, maxLifecycleState)
//        }
//    }

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

    fun registerFragmentTransactionCallback() {
//        registerFragmentTransactionCallback(fragmentTransactionCallback)
    }

    fun unregisterFragmentTransactionCallback() {
//        unregisterFragmentTransactionCallback(fragmentTransactionCallback)
    }

    override fun getItemCount(): Int = 6

    override fun createFragment(position: Int): Fragment {

        return when (position) {
            0 -> PostVerticalNavHostFragment()
            1 -> PostHorizontalNavHostFragment()
            2 -> PostGridNavHostFragment()
            3 -> PostStaggeredNavHostFragment()
            4 -> NotificationHostFragment()
            else -> LoginFragment1()

        }
    }

//    override fun createFragment(position: Int): Fragment {
//
//        return when (position) {
//            0 -> DashboardFragment1()
//            1 -> NotificationFragment1()
//            2 -> HomeNavHostFragment()
//            3 -> DashboardNavHostFragment()
//            4 -> LoginFragment1()
//            else -> NotificationHostFragment()
//
//        }
//    }

}