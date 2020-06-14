package com.smarttoolfactory.tutorial0_materialdesign.chapter2_1viewpager2_fragments.adapter.fragment_state_adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.smarttoolfactory.tutorial0_materialdesign.blankfragment.GenericFragmentWithArgs
import com.smarttoolfactory.tutorial0_materialdesign.fragment_factory.GenericFragmentFactory

/**
 * Uses [GenericFragmentFactory] to instantiate fragments that have constructor arguments.
 */
class StaticFragmentFactoryStateAdapter(private val fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {

    private val genericFragmentFactory = GenericFragmentFactory.getFragmentFactory()

    override fun getItemCount(): Int {
        return 8
    }

    override fun createFragment(position: Int): Fragment {
        println("👍 ${this.javaClass.simpleName} createFragment() $position")

        genericFragmentFactory.fragID = position

        return genericFragmentFactory.instantiate(
            fragmentActivity.classLoader,
            GenericFragmentWithArgs::class.java.name
        )
    }

}