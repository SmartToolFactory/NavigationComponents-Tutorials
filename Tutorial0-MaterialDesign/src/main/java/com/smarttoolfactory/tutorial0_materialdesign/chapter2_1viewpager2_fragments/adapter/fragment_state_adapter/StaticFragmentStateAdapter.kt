package com.smarttoolfactory.tutorial0_materialdesign.chapter2_1viewpager2_fragments.adapter.fragment_state_adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.smarttoolfactory.tutorial0_materialdesign.blankfragment.GenericFragment

/**
 * Basic usage of [FragmentStateAdapter] with fragments and static count
 */
class StaticFragmentStateAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {


    override fun getItemCount(): Int {
        return 8
    }

    override fun createFragment(position: Int): Fragment {
        println("👍 StaticFragmentAdapter createFragment() $position")
        return GenericFragment.newInstance(position)
    }

}