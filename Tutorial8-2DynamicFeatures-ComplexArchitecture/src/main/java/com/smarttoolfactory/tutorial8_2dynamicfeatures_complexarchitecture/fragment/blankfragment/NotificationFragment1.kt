package com.smarttoolfactory.tutorial8_2dynamicfeatures_complexarchitecture.fragment.blankfragment

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.smarttoolfactory.tutorial8_2dynamicfeatures_complexarchitecture.R
import com.smarttoolfactory.tutorial8_2dynamicfeatures_complexarchitecture.databinding.FragmentNotification1Binding

class NotificationFragment1 : BaseDataBindingFragment<FragmentNotification1Binding>() {

    override fun getLayoutRes(): Int = R.layout.fragment_notification1

    private var count = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dataBinding.tvCount.text = "Count: $count"

        dataBinding.btnIncrease.setOnClickListener {
            dataBinding.tvCount.text = "Count: ${++count}"
        }

        dataBinding.btnNextPage.setOnClickListener {
            findNavController().navigate(R.id.action_notificationFragment1_to_notificationFragment2)
        }

        dataBinding.btnCamera.setOnClickListener {
            val bundle = bundleOf("count" to count)

            findNavController().navigate(
                R.id.nav_graph_camera,
                bundle
            )
        }

    }

}
