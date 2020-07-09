package com.smarttoolfactory.tutorial8_2dynamicfeatures_complexarchitecture.fragment.blankfragment

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.smarttoolfactory.tutorial8_2dynamicfeatures_complexarchitecture.R
import com.smarttoolfactory.tutorial8_2dynamicfeatures_complexarchitecture.databinding.FragmentDashboard1Binding

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

        dataBinding.btnPhotos.setOnClickListener {

            val bundle = bundleOf("count" to count)

            findNavController().navigate(
                R.id.nav_graph_photos,
                bundle
            )
        }

        // 🔥 Listen savedStateHandle liveData,any type can be put in a Bundle is supported
        findNavController().currentBackStackEntry?.savedStateHandle?.getLiveData<Int>("count")
            ?.observe(
                viewLifecycleOwner, Observer { result: Int ->
                    // Do something with the result.
                    count = result
                    dataBinding.tvCount.text = "Count: $count"

                })

    }


}
