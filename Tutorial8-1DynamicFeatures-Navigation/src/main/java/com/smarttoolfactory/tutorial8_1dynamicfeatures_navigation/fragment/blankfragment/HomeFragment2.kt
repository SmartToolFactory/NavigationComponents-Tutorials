package com.smarttoolfactory.tutorial8_1dynamicfeatures_navigation.fragment.blankfragment

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import com.smarttoolfactory.tutorial8_1dynamicfeatures_navigation.R
import com.smarttoolfactory.tutorial8_1dynamicfeatures_navigation.databinding.FragmentHome2Binding

class HomeFragment2 : BaseDataBindingFragment<FragmentHome2Binding>() {

    private var count:Int = 0

    override fun getLayoutRes(): Int = R.layout.fragment_home2

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        count = arguments?.get("count") as Int

        val binding = dataBinding!!

        binding.tvCount.text = "Count: $count"

        binding.btnIncrease.setOnClickListener {
            binding.tvCount.text = "Count: ${++count}"

            // 🔥 Put value to savedStateHandle
            findNavController().previousBackStackEntry?.savedStateHandle?.set("count", count)

            // TODO Not Working
//            setFragmentResult("count", bundleOf("count" to count))

        }

        binding.btnNextPage.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment2_to_homeFragment3)
        }

        binding.btnGallery.setOnClickListener {
            val bundle = bundleOf("count" to count)

            findNavController().navigate(
                R.id.nav_graph_gallery,
                bundle
            )
        }

    }

    override fun onDestroyView() {
        findNavController().previousBackStackEntry?.savedStateHandle?.set("count", count)
        super.onDestroyView()
    }

}
