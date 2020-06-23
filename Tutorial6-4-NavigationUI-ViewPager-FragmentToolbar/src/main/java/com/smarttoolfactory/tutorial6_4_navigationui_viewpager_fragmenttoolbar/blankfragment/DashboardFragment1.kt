package com.smarttoolfactory.tutorial6_4_navigationui_viewpager_fragmenttoolbar.blankfragment

import android.os.Bundle
import android.view.View
import com.smarttoolfactory.tutorial6_4_navigationui_viewpager_fragmenttoolbar.R
import com.smarttoolfactory.tutorial6_4_navigationui_viewpager_fragmenttoolbar.databinding.FragmentDashboard1Binding

class DashboardFragment1 : BaseDataBindingFragment<FragmentDashboard1Binding>() {

    override fun getLayoutRes(): Int = R.layout.fragment_dashboard1

    var count = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dataBinding.btnIncrease.setOnClickListener {
            dataBinding.tvTitle.text = "Count: ${count++}"
        }

        dataBinding.btnNextPage.setOnClickListener {
//            findNavController().navigate(R.id.action_dashboardFragment1_to_dashboardFragment2)
        }

    }


}
