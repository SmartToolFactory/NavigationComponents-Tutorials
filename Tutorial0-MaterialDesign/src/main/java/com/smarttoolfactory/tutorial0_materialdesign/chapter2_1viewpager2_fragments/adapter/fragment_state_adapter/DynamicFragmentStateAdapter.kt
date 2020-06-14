package com.smarttoolfactory.tutorial0_materialdesign.chapter2_1viewpager2_fragments.adapter.fragment_state_adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.smarttoolfactory.tutorial0_materialdesign.blankfragment.GenericFragmentWithArgs
import com.smarttoolfactory.tutorial0_materialdesign.fragment_factory.GenericFragmentFactory
import java.util.*


class DynamicFragmentStateAdapter(private val fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {

    private val genericFragmentFactory = GenericFragmentFactory.getFragmentFactory()

    /**
     *  Initial list that contains classes of fragments to be added to viewPager
     */
    private val fragmentClazzList = ArrayList<Class<out Fragment>>()
        .apply {
            add(GenericFragmentWithArgs::class.java)
            add(GenericFragmentWithArgs::class.java)
            add(GenericFragmentWithArgs::class.java)
        }

    internal inline fun <reified F : Fragment> addFragment() {
        fragmentClazzList.add(F::class.java)
        notifyDataSetChanged()
    }

    fun removeFragment() {
        if (fragmentClazzList.size > 1)
            fragmentClazzList.removeAt(fragmentClazzList.size - 1)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = fragmentClazzList.size

    override fun createFragment(position: Int): Fragment {
        println("👍 DynamicFragmentStateAdapter createFragment() $position, class: ${fragmentClazzList[position].name}")

        genericFragmentFactory.fragID = position

        return genericFragmentFactory.instantiate(
            fragmentActivity.classLoader,
            fragmentClazzList[position].name
        )
    }

}