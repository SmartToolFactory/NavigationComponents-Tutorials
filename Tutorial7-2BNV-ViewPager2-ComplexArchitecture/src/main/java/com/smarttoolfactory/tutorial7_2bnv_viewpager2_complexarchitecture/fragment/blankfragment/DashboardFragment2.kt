package com.smarttoolfactory.tutorial7_2bnv_viewpager2_complexarchitecture.fragment.blankfragment

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.smarttoolfactory.tutorial7_2bnv_viewpager2_complexarchitecture.R
import com.smarttoolfactory.tutorial7_2bnv_viewpager2_complexarchitecture.databinding.FragmentDashboard2Binding

class DashboardFragment2 : BaseDataBindingFragment<FragmentDashboard2Binding>() {

    override fun getLayoutRes(): Int = R.layout.fragment_dashboard2

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dataBinding!!.btnNextPage.setOnClickListener {
            findNavController().navigate(R.id.action_dashboardFragment2_to_dashboardFragment3)
        }
    }
}
