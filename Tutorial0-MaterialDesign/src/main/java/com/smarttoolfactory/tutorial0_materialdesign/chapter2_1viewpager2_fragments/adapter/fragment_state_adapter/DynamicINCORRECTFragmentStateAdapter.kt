package com.smarttoolfactory.tutorial0_materialdesign.chapter2_1viewpager2_fragments.adapter.fragment_state_adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import java.util.*


/**
 * Adapter to add or remove tabs or fragments dynamically
 * ### This Adapter does not instantiate fragments but receives them via addFragment method
 */
class DynamicINCORRECTFragmentStateAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    private val fragmentList = ArrayList<Fragment>()

    fun addFragment(fragment: Fragment) {
        fragmentList.add(fragment)
        notifyDataSetChanged()
    }

    fun getFragmentCount() = fragmentList.size

    fun removeFragment() {
        if (fragmentList.size > 1)
            fragmentList.removeAt(fragmentList.size - 1)
        notifyDataSetChanged()
    }


    override fun getItemCount(): Int = fragmentList.size

    /*
        ❌❌❌ Don't use this
     */
    /*
     *  [FragmentStateAdapter.createFragment] should create fragments instead of getting from
     * another source
     *
     * [Make sure that your new createFragment() method always supplies a new fragment
     * instance each time the function is called instead of reusing instances.](https://developer.android.com/training/animation/vp2-migration)
     */
    override fun createFragment(position: Int): Fragment {
        println("👍 DynamicFragmentStateAdapter createFragment() $position")
        return fragmentList[position]
    }

}