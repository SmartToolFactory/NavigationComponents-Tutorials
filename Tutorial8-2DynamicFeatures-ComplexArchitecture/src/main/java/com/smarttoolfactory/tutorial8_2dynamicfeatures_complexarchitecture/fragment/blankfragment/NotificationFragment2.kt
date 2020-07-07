package com.smarttoolfactory.tutorial8_2dynamicfeatures_complexarchitecture.fragment.blankfragment

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.smarttoolfactory.tutorial8_2dynamicfeatures_complexarchitecture.R
import com.smarttoolfactory.tutorial8_2dynamicfeatures_complexarchitecture.databinding.FragmentNotification2Binding
import com.smarttoolfactory.tutorial8_2dynamicfeatures_complexarchitecture.fragment.blankfragment.BaseDataBindingFragment

class NotificationFragment2 : BaseDataBindingFragment<FragmentNotification2Binding>() {

    override fun getLayoutRes(): Int = R.layout.fragment_notification2

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dataBinding.btnNextPage.setOnClickListener {
            findNavController().navigate(R.id.action_notificationFragment2_to_notificationFragment3)
        }

    }

}
