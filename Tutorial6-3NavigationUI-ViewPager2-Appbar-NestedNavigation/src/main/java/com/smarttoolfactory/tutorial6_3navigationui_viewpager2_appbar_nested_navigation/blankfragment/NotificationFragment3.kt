package com.smarttoolfactory.tutorial6_3navigationui_viewpager2_appbar_nested_navigation.blankfragment

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.smarttoolfactory.tutorial6_3navigationui_viewpager2_appbar.R
import com.smarttoolfactory.tutorial6_3navigationui_viewpager2_appbar.databinding.FragmentNotification3Binding

class NotificationFragment3 : BaseDataBindingFragment<FragmentNotification3Binding>() {
    override fun getLayoutRes(): Int = R.layout.fragment_notification3

    var count = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        dataBinding.btnIncrease.setOnClickListener {
            dataBinding.tvTitle.text = "Count: ${count++}"
        }

        dataBinding.btnGoToStart.setOnClickListener {
            findNavController().navigate(R.id.action_notificationFragment3_to_notificationFragment1)
        }
    }
}
