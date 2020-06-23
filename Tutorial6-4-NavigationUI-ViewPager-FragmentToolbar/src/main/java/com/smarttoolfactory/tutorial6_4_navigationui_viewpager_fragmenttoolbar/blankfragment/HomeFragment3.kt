package com.smarttoolfactory.tutorial6_4_navigationui_viewpager_fragmenttoolbar.blankfragment

import android.os.Bundle
import android.view.View
import com.smarttoolfactory.tutorial6_4_navigationui_viewpager_fragmenttoolbar.R
import com.smarttoolfactory.tutorial6_4_navigationui_viewpager_fragmenttoolbar.databinding.FragmentHome3Binding

class HomeFragment3 : BaseDataBindingFragment<FragmentHome3Binding>() {
    override fun getLayoutRes(): Int = R.layout.fragment_home3

    var count = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        dataBinding.btnIncrease.setOnClickListener {
            dataBinding.tvTitle.text = "Count: ${count++}"
        }

        dataBinding.btnGoToStart.setOnClickListener {
//            findNavController().navigate(R.id.action_global_start)
        }
    }
}
