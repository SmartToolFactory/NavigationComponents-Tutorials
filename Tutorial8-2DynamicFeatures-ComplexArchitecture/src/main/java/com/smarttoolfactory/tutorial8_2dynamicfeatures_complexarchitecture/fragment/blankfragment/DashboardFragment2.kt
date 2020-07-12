package com.smarttoolfactory.tutorial8_2dynamicfeatures_complexarchitecture.fragment.blankfragment

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.smarttoolfactory.tutorial8_2dynamicfeatures_complexarchitecture.R
import com.smarttoolfactory.tutorial8_2dynamicfeatures_complexarchitecture.databinding.FragmentDashboard2Binding

class DashboardFragment2 : BaseDataBindingFragment<FragmentDashboard2Binding>() {

    override fun getLayoutRes(): Int = R.layout.fragment_dashboard2

    private var count = 0


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        count = arguments?.get("count") as Int

        val binding = dataBinding!!

        binding.tvCount.text = "Count: $count"

        binding.btnIncrease.setOnClickListener {
            binding.tvCount.text = "Count: ${++count}"

            // 🔥 Put value to savedStateHandle
            findNavController().previousBackStackEntry?.savedStateHandle?.set("count", count)
        }

        binding.btnNextPage.setOnClickListener {
            findNavController().navigate(R.id.action_dashboardFragment2_to_dashboardFragment3)
        }

        binding.btnPhotos.setOnClickListener {
            val bundle = bundleOf("count" to count)

            findNavController().navigate(
                R.id.nav_graph_photos,
                bundle
            )
        }
    }


}
