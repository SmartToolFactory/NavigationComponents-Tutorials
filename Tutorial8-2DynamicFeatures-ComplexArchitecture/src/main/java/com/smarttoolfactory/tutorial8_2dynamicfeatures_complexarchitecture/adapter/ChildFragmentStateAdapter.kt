package com.smarttoolfactory.tutorial8_2dynamicfeatures_complexarchitecture.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.commitNow
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter.FragmentTransactionCallback.OnPostEventListener
import com.smarttoolfactory.tutorial8_2dynamicfeatures_complexarchitecture.fragment.blankfragment.LoginFragment1
import com.smarttoolfactory.tutorial8_2dynamicfeatures_complexarchitecture.fragment.navhost.*


class ChildFragmentStateAdapter(fragment: Fragment) :
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

    override fun getItemCount(): Int = 6

    override fun createFragment(position: Int): Fragment {

        return when (position) {
            0 -> PostVerticalNavHostFragment()
            1 -> PostHorizontalNavHostFragment()
            2 -> PostGridNavHostFragment()
            3 -> PostStaggeredNavHostFragment()
            4-> NotificationHostFragment()
            else -> LoginFragment1()

        }
    }

}