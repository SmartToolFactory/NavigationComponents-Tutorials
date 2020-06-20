package com.smarttoolfactory.tutorial1_3navigation_nestednavhost.blankfragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.smarttoolfactory.tutorial1_3navigation_nestednavhost.R


class MainFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

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
