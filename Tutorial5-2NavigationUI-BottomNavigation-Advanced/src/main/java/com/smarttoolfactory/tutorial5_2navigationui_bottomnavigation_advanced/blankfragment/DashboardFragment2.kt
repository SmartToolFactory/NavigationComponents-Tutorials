package com.smarttoolfactory.tutorial5_2navigationui_bottomnavigation_advanced.blankfragment

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.smarttoolfactory.tutorial5_2navigationui_bottomnavigation_advanced.R
import com.smarttoolfactory.tutorial5_2navigationui_bottomnavigation_advanced.databinding.FragmentDashboard2Binding

class DashboardFragment2 : BaseDataBindingFragment<FragmentDashboard2Binding>() {

    override fun getLayoutRes(): Int = R.layout.fragment_dashboard2

    var count = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dataBinding.btnIncrease.setOnClickListener {
            dataBinding.tvTitle.text = "Count: ${count++}"
        }

        dataBinding.btnNextPage.setOnClickListener {
//            findNavController().navigate(R.id.dashboardFragment3)
        }

        println("🔥 DashboardFragment1 navController: ${findNavController()}")


    }


}
