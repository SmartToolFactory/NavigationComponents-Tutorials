package com.smarttoolfactory.gallery

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.smarttoolfactory.gallery.databinding.FragmentGallery1Binding
import com.smarttoolfactory.tutorial8_1dynamicfeatures_navigation.fragment.blankfragment.BaseDataBindingFragment

class GalleryFragment1 : BaseDataBindingFragment<FragmentGallery1Binding>() {
    override fun getLayoutRes(): Int = R.layout.fragment_gallery1


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val count = arguments?.get("count") as Int

        dataBinding.tvInfo.text = "${dataBinding.tvInfo.text}\n Count: $count"

        dataBinding.btnNextPage.setOnClickListener {
            findNavController().navigate(R.id.action_galleryFragment1_to_galleryFragment2)
        }

        dataBinding.btnFavorites.setOnClickListener {
            val bundle = bundleOf("count" to count)

            findNavController().navigate(
                R.id.action_galleryFragment1_to_nav_graph_favorites,
                bundle
            )
        }
    }
}