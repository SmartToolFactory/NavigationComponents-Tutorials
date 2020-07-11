package com.smarttoolfactory.tutorial6_7navigationui_memoryleakcheck.blankfragment

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.smarttoolfactory.tutorial6_7navigationui_memoryleakcheck.R

class DashboardFragment1 : BaseFragment() {

    override fun getLayoutRes(): Int = R.layout.fragment_dashboard1


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        view.findViewById<Button>(R.id.btnNextPage).setOnClickListener {
//            findNavController().navigate(R.id.action_mainFragment_to_dashboardFragment2)
        }
    }

}
