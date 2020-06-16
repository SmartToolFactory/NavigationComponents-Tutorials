package com.smarttoolfactory.tutorial6_1navigationui_viewpager2.blankfragment

import android.os.Bundle
import android.view.View
import com.smarttoolfactory.tutorial6_1navigationui_viewpager2.R
import com.smarttoolfactory.tutorial6_1navigationui_viewpager2.databinding.FragmentNotification3Binding


class NotificationFragment3 : BaseDataBindingFragment<FragmentNotification3Binding>() {

    override fun getLayoutRes(): Int = R.layout.fragment_notification3


    var count = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataBinding.btnIncrease.setOnClickListener {
            dataBinding.tvTitle.text = "Count: ${count++}"
        }
    }

}