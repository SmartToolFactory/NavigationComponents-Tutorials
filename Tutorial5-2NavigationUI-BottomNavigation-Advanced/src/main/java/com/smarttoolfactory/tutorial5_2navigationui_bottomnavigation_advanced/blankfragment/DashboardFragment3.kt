package com.smarttoolfactory.tutorial5_2navigationui_bottomnavigation_advanced.blankfragment

import android.os.Bundle
import android.view.View
import com.smarttoolfactory.tutorial5_2navigationui_bottomnavigation_advanced.R
import com.smarttoolfactory.tutorial5_2navigationui_bottomnavigation_advanced.databinding.FragmentDashboard3Binding

class DashboardFragment3 : BaseDataBindingFragment<FragmentDashboard3Binding>() {

    override fun getLayoutRes(): Int = R.layout.fragment_dashboard3

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
