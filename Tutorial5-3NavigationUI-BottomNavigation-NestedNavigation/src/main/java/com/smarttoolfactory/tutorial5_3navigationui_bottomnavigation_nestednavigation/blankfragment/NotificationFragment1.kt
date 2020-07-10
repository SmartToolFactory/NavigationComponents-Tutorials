package com.smarttoolfactory.tutorial5_3navigationui_bottomnavigation_nestednavigation.blankfragment

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.smarttoolfactory.tutorial5_3navigationui_bottomnavigation_nestednavigation.R

class NotificationFragment1 : BaseFragment() {

    override fun getLayoutRes(): Int = R.layout.fragment_notification1

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.btnNextPage).setOnClickListener {
            findNavController().navigate(R.id.action_notificationFragment1_to_notificationFragment2)
        }
    }
}
