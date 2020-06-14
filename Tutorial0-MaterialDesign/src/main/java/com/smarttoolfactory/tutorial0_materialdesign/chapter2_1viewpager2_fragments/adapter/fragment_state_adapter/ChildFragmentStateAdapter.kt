package com.smarttoolfactory.tutorial0_materialdesign.chapter2_1viewpager2_fragments.adapter.fragment_state_adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.smarttoolfactory.tutorial0_materialdesign.blankfragment.GenericFragmentParent
import com.smarttoolfactory.tutorial0_materialdesign.fragment_factory.GenericFragmentFactory


/**
 *  Passing [Fragment] as parameter let's this adapter to use childFragmentManager
 */
class ChildFragmentStateAdapter(private val fragment: Fragment) :
    FragmentStateAdapter(fragment) {

    private val genericFragmentFactory = GenericFragmentFactory.getFragmentFactory()


    override fun getItemCount(): Int = 5

    override fun createFragment(position: Int): Fragment {

        genericFragmentFactory.fragID = position

        return genericFragmentFactory.instantiate(
            fragment.requireActivity().classLoader,
            GenericFragmentParent::class.java.name
        )
    }

}