package com.smarttoolfactory.tutorial1_2navigation_nestednavgraph.blankfragment

import android.os.Bundle
import android.view.View
import com.smarttoolfactory.tutorial1_2navigation_nestednavgraph.R
import com.smarttoolfactory.tutorial1_2navigation_nestednavgraph.databinding.FragmentDashboard2Binding

class DashboardFragment2 : BaseDataBindingFragment<FragmentDashboard2Binding>() {

    override fun getLayoutRes(): Int = R.layout.fragment_dashboard2

    var count = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dataBinding.btnIncrease.setOnClickListener {
            dataBinding.tvTitle.text = "Count: ${count++}"
        }

    }
}
