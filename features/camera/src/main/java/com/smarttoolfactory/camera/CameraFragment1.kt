package com.smarttoolfactory.camera

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.smarttoolfactory.camera.databinding.FragmentCamera1Binding
import com.smarttoolfactory.tutorial8_2dynamicfeatures_complexarchitecture.fragment.blankfragment.BaseDataBindingFragment

class CameraFragment1 : BaseDataBindingFragment<FragmentCamera1Binding>() {
    override fun getLayoutRes(): Int = R.layout.fragment_camera1


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val count = (arguments?.get("count") as? Int) ?: 0

        val binding = dataBinding!!

        binding.tvInfo.text = "${binding.tvInfo.text}\n Count: $count"

        binding.btnNextPage.setOnClickListener {
            findNavController().navigate(R.id.action_cameraFragment1_to_cameraFragment2)
        }
    }
}