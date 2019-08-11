package com.smarttoolfactory.tutorial4_1navigationui_navigationview.blankfragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.smarttoolfactory.tutorial4_1navigationui_navigationview.R


class CameraFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_camera, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Toast.makeText(activity, "CameraFragment onViewCreated() this: $this", Toast.LENGTH_SHORT).show()
        println("🔥 CameraFragment onViewCreated() this: $this")
    }

}
