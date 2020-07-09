package com.smarttoolfactory.photos

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.smarttoolfactory.photos.databinding.FragmentPhotos1Binding
import com.smarttoolfactory.tutorial8_2dynamicfeatures_complexarchitecture.fragment.blankfragment.BaseDataBindingFragment

class PhotosFragment1 : BaseDataBindingFragment<FragmentPhotos1Binding>() {
    override fun getLayoutRes(): Int = R.layout.fragment_photos1

    var count: Int = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        count = arguments?.get("count") as Int

        dataBinding.tvCount.text = "Count: $count"

        dataBinding.btnIncrease.setOnClickListener {
            dataBinding.tvCount.text = "Count: ${++count}"

            // 🔥 SetFragmentResult
//            setFragmentResult("count", bundleOf("count" to count))
            // 🔥 Set result for savedStateHandle
            findNavController().previousBackStackEntry?.savedStateHandle?.set("count", count)
        }

        dataBinding.btnNextPage.setOnClickListener {
            findNavController().navigate(R.id.action_photosFragment1_to_photosFragment2)
        }

        dataBinding.btnCamera.setOnClickListener {
            val bundle = bundleOf("count" to count)

//            findNavController().navigate(
//                R.id.action_photosFragment1_to_nav_graph_camera,
//                bundle
//            )
        }
    }

}