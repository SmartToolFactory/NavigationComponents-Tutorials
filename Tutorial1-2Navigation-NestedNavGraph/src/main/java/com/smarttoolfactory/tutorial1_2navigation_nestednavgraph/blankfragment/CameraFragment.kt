package com.smarttoolfactory.tutorial1_2navigation_nestednavgraph.blankfragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.smarttoolfactory.tutorial1_2navigation_nestednavgraph.R
import com.smarttoolfactory.tutorial1_2navigation_nestednavgraph.databinding.FragmentCameraBinding

class CameraFragment : BaseDataBindingFragment<FragmentCameraBinding>() {

    override fun getLayoutRes(): Int = R.layout.fragment_camera

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Toast.makeText(activity, "CameraFragment onViewCreated() this: $this", Toast.LENGTH_SHORT)
            .show()
        println("🔥 CameraFragment onViewCreated() this: $this")
    }

}
