package com.smarttoolfactory.tutorial1_2navigation_nestednavgraph.blankfragment


import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.navigation.Navigation
import com.smarttoolfactory.tutorial1_2navigation_nestednavgraph.R
import com.smarttoolfactory.tutorial1_2navigation_nestednavgraph.databinding.FragmentMainBinding


class MainFragment : BaseDataBindingFragment<FragmentMainBinding>() {

    override fun getLayoutRes(): Int = R.layout.fragment_main

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val buttonDestCamera = view.findViewById<Button>(R.id.btnDestCam)

        buttonDestCamera?.setOnClickListener(
            Navigation.createNavigateOnClickListener(
                R.id.action_main_dest_to_cameraFragment,
                null
            )
        )

        // Home Nav Graph
        val buttonHomeNavGraph = view.findViewById<Button>(R.id.btnNavGraphHome)

        buttonHomeNavGraph?.setOnClickListener(
            Navigation.createNavigateOnClickListener(
                R.id.action_main_dest_to_nav_graph_home,
                null
            )
        )

        // Dashboard Nav Graph
        val buttonDashboardNavGraph = view.findViewById<Button>(R.id.btnNavGraphDashboard)
        buttonDashboardNavGraph?.setOnClickListener(
            Navigation.createNavigateOnClickListener(
                R.id.action_main_dest_to_nav_graph_dashboard,
                null
            )
        )
    }
}
