package com.smarttoolfactory.tutorial0_1viewpager2.blankfragment

import android.os.Bundle
import android.view.View
import com.smarttoolfactory.tutorial0_1viewpager2.R
import com.smarttoolfactory.tutorial0_1viewpager2.databinding.FragmentHome2Binding

class HomeFragment2 : BaseDataBindingFragment<FragmentHome2Binding>() {

    override fun getLayoutRes(): Int = R.layout.fragment_home2

    var count = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dataBinding.btnIncrease.setOnClickListener {
            dataBinding.tvTitle.text = "Count: ${count++}"
        }

        dataBinding.btnNextPage.setOnClickListener {

        }

    }

}
