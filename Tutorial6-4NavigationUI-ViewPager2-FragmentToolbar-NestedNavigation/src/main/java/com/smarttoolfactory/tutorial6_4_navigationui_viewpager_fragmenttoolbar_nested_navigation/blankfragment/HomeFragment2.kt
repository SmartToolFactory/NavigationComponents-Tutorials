package com.smarttoolfactory.tutorial6_4_navigationui_viewpager_fragmenttoolbar_nested_navigation.blankfragment

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.smarttoolfactory.tutorial6_4_navigationui_viewpager_fragmenttoolbar.R
import com.smarttoolfactory.tutorial6_4_navigationui_viewpager_fragmenttoolbar.databinding.FragmentHome2Binding

class HomeFragment2 : BaseDataBindingFragment<FragmentHome2Binding>() {

    override fun getLayoutRes(): Int = R.layout.fragment_home2

    var count = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dataBinding.btnIncrease.setOnClickListener {
            dataBinding.tvTitle.text = "Count: ${count++}"
        }

        dataBinding.btnNextPage.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment2_to_homeFragment3)
        }

    }

}
