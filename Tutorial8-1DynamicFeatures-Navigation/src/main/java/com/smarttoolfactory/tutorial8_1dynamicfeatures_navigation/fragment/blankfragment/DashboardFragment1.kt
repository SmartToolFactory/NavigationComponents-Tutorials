package com.smarttoolfactory.tutorial8_1dynamicfeatures_navigation.fragment.blankfragment

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.smarttoolfactory.tutorial8_1dynamicfeatures_navigation.R
import com.smarttoolfactory.tutorial8_1dynamicfeatures_navigation.databinding.FragmentDashboard1Binding

class DashboardFragment1 : BaseDataBindingFragment<FragmentDashboard1Binding>() {

    override fun getLayoutRes(): Int = R.layout.fragment_dashboard1

    private var count = 0


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dataBinding.tvCount.text = "Count: $count"

        dataBinding.btnIncrease.setOnClickListener {
            dataBinding.tvCount.text = "Count: ${++count}"
        }


        dataBinding.btnNextPage.setOnClickListener {
            findNavController().navigate(R.id.action_dashboardFragment1_to_dashboardFragment2)
        }

        dataBinding.btnGallery.setOnClickListener {
            val bundle = bundleOf("count" to count)

            findNavController().navigate(
                R.id.nav_graph_gallery,
                bundle
            )
        }

    }


}
