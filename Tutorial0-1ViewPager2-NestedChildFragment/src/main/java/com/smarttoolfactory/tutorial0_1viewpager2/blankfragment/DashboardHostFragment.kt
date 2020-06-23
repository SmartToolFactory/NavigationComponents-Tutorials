package com.smarttoolfactory.tutorial0_1viewpager2.blankfragment

import android.os.Bundle
import android.view.View
import com.smarttoolfactory.tutorial0_1viewpager2.R
import com.smarttoolfactory.tutorial0_1viewpager2.databinding.FragmentDashboardHostBinding

class DashboardHostFragment : BaseDataBindingFragment<FragmentDashboardHostBinding>() {
    override fun getLayoutRes(): Int = R.layout.fragment_dashboard_host

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        childFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container_dashboard, DashboardFragment1())
            .commit()
    }

}