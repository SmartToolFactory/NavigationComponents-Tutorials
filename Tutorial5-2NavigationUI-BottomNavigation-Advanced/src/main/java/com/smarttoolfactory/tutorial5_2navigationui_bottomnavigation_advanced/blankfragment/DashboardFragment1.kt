package com.smarttoolfactory.tutorial5_2navigationui_bottomnavigation_advanced.blankfragment

import android.os.Bundle
import android.view.View
import com.smarttoolfactory.tutorial5_2navigationui_bottomnavigation_advanced.R
import com.smarttoolfactory.tutorial5_2navigationui_bottomnavigation_advanced.databinding.FragmentDashboard1Binding

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
