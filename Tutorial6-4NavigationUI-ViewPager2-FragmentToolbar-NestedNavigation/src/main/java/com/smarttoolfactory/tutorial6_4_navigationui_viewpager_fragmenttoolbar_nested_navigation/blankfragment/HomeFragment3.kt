package com.smarttoolfactory.tutorial6_4_navigationui_viewpager_fragmenttoolbar_nested_navigation.blankfragment

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.smarttoolfactory.tutorial6_4_navigationui_viewpager_fragmenttoolbar.R
import com.smarttoolfactory.tutorial6_4_navigationui_viewpager_fragmenttoolbar.databinding.FragmentHome3Binding

class HomeFragment3 : BaseDataBindingFragment<FragmentHome3Binding>() {
    override fun getLayoutRes(): Int = R.layout.fragment_home3

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dataBinding!!.btnGoToStart.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment3_to_homeFragment1)
        }
    }
}
