package com.smarttoolfactory.gallery

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import com.smarttoolfactory.gallery.databinding.FragmentGallery1Binding
import com.smarttoolfactory.tutorial8_1dynamicfeatures_navigation.fragment.blankfragment.BaseDataBindingFragment

class GalleryFragment1 : BaseDataBindingFragment<FragmentGallery1Binding>() {
    override fun getLayoutRes(): Int = R.layout.fragment_gallery1

    var count: Int = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        count = arguments?.get("count") as Int

        val binding = dataBinding!!

        binding.tvCount.text = "Count: $count"

        binding.btnIncrease.setOnClickListener {
            binding.tvCount.text = "Count: ${++count}"

            // 🔥 SetFragmentResult
//            setFragmentResult("count", bundleOf("count" to count))
            // 🔥 Set result for savedStateHandle
            findNavController().previousBackStackEntry?.savedStateHandle?.set("count", count)
        }

        binding.btnNextPage.setOnClickListener {
            findNavController().navigate(R.id.action_galleryFragment1_to_galleryFragment2)
        }

        binding.btnFavorites.setOnClickListener {
            val bundle = bundleOf("count" to count)

            findNavController().navigate(
                R.id.action_galleryFragment1_to_nav_graph_favorites,
                bundle
            )
        }
    }

}