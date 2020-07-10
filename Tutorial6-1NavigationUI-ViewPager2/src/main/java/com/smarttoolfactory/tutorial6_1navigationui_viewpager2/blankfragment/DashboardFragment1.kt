package com.smarttoolfactory.tutorial6_1navigationui_viewpager2.blankfragment

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.smarttoolfactory.tutorial6_1navigationui_viewpager2.R
import com.smarttoolfactory.tutorial6_1navigationui_viewpager2.databinding.FragmentDashboard1Binding


class DashboardFragment1 : BaseDataBindingFragment<FragmentDashboard1Binding>() {

    override fun getLayoutRes(): Int = R.layout.fragment_dashboard1

    var count = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dataBinding!!.btnIncrease.setOnClickListener {
            dataBinding!!.tvTitle.text = "Count: ${count++}"
        }

        dataBinding!!.btnNextPage.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_dashboardFragment2)
        }
    }
}
