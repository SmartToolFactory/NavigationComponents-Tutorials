package com.smarttoolfactory.tutorial0_1viewpager2.blankfragment

import android.os.Bundle
import android.view.View
import com.smarttoolfactory.tutorial0_1viewpager2.R
import com.smarttoolfactory.tutorial0_1viewpager2.databinding.FragmentHome1Binding

class HomeFragment1 : BaseDataBindingFragment<FragmentHome1Binding>() {

    override fun getLayoutRes(): Int = R.layout.fragment_home1

    var count = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        println("🏠 HomeFragment1 childFragmentManager: $childFragmentManager, parentFragmentManager: $parentFragmentManager, parentFragment: $parentFragment")


        dataBinding.btnIncrease.setOnClickListener {
            dataBinding.tvTitle.text = "Count: ${count++}"
        }


        dataBinding.btnNextPage.setOnClickListener {


            parentFragment?.let {
                it.childFragmentManager
                    .beginTransaction()
                    .replace(R.id.fragment_container_home, HomeFragment2())
                    .commit()
            }
        }

    }

}
