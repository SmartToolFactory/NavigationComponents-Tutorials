package com.smarttoolfactory.tutorial5_4navigationui_memoryleakcheck.fragment.blankfragment

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.smarttoolfactory.tutorial5_4navigationui_memoryleakcheck.fragment.blankfragment.BaseDataBindingFragment
import com.smarttoolfactory.tutorial5_4navigationui_memoryleakcheck.R
import com.smarttoolfactory.tutorial5_4navigationui_memoryleakcheck.databinding.FragmentDashboard3Binding

class DashboardFragment3 : BaseDataBindingFragment<FragmentDashboard3Binding>() {

    override fun getLayoutRes(): Int = R.layout.fragment_dashboard3

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = dataBinding!!

        binding.btnGoToStart.setOnClickListener {
            findNavController().navigate(R.id.action_dashboardFragment3_to_dashboardFragment1)
        }

    }
}
