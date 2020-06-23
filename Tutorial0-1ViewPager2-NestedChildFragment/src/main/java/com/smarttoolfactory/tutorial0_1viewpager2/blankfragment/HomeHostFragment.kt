package com.smarttoolfactory.tutorial0_1viewpager2.blankfragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.smarttoolfactory.tutorial0_1viewpager2.R
import com.smarttoolfactory.tutorial0_1viewpager2.databinding.FragmentHomeHostBinding

class HomeHostFragment : BaseDataBindingFragment<FragmentHomeHostBinding>() {

    override fun getLayoutRes(): Int = R.layout.fragment_home_host

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        childFragmentManager.addOnBackStackChangedListener {

            val fragmentList = childFragmentManager.fragments
            val fragmentCount = fragmentList.size
            val backStackEntryCount = childFragmentManager.backStackEntryCount

            println("🏠 FragmentCount: $fragmentCount, backStackEntryCount: $backStackEntryCount\n fragmentList: $fragmentList")

            Toast.makeText(requireContext(), "🏠 ${this.javaClass.simpleName} FragmentCount: $fragmentCount, backStackEntryCount: $backStackEntryCount\n fragmentList: $fragmentList", Toast.LENGTH_SHORT).show()

        }

        childFragmentManager
            .beginTransaction()
            .add(R.id.fragment_container_home, HomeFragment1())
            .commit()

        println("🏠HomeHostFragment childFragmentManager: $childFragmentManager")

    }
}
