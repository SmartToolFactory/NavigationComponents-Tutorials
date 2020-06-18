package com.smarttoolfactory.tutorial6_1navigationui_viewpager2.blankfragment

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.smarttoolfactory.tutorial6_1navigationui_viewpager2.R
import com.smarttoolfactory.tutorial6_1navigationui_viewpager2.databinding.FragmentDashboard2Binding


class DashboardFragment2 : BaseDataBindingFragment<FragmentDashboard2Binding>() {

    override fun getLayoutRes(): Int = R.layout.fragment_dashboard2

    var count = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dataBinding.btnIncrease.setOnClickListener {
            dataBinding.tvTitle.text = "Count: ${count++}"
        }

        dataBinding.btnNextPage.setOnClickListener {
            findNavController().navigate(R.id.action_dashboardFragment2_to_dashboardFragment3)
        }

        println("🔥 DashboardFragment1 navController: ${findNavController()}")


    }


}
