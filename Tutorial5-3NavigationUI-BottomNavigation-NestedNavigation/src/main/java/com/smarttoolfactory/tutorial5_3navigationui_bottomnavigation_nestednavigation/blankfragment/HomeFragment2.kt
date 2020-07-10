package com.smarttoolfactory.tutorial5_3navigationui_bottomnavigation_nestednavigation.blankfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.smarttoolfactory.tutorial5_3navigationui_bottomnavigation_nestednavigation.R

class HomeFragment2 : BaseFragment() {

    override fun getLayoutRes(): Int = R.layout.fragment_home2

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.btnNextPage).setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment2_to_homeFragment3)
        }
    }
}
