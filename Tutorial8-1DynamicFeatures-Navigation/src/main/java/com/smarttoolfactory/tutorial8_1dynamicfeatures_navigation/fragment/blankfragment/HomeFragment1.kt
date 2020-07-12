package com.smarttoolfactory.tutorial8_1dynamicfeatures_navigation.fragment.blankfragment

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.smarttoolfactory.tutorial8_1dynamicfeatures_navigation.R
import com.smarttoolfactory.tutorial8_1dynamicfeatures_navigation.databinding.FragmentHome1Binding

class HomeFragment1 : BaseDataBindingFragment<FragmentHome1Binding>() {

    override fun getLayoutRes(): Int = R.layout.fragment_home1

    private var count = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = dataBinding!!

        binding.tvCount.text = "Count: $count"

        binding.btnIncrease.setOnClickListener {
            binding.tvCount.text = "Count: ${++count}"
        }

        binding.btnNextPage.setOnClickListener {
            val bundle = bundleOf("count" to count)
            findNavController().navigate(R.id.action_homeFragment1_to_homeFragment2, bundle)
        }

        binding.btnGallery.setOnClickListener {
            val bundle = bundleOf("count" to count)

            findNavController().navigate(
                R.id.nav_graph_gallery,
                bundle
            )
        }

        // 🔥 Listen savedStateHandle liveData, any type can be put in a Bundle is supported
        findNavController().currentBackStackEntry?.savedStateHandle?.getLiveData<Int>("count")
            ?.observe(
                viewLifecycleOwner, Observer { result: Int ->
                    // Do something with the result.
                    count = result
                    binding.tvCount.text = "Count: $count"

                })


        // TODO Not Working
        setFragmentResultListener("count") { key, bundle ->
            count = bundle.getInt("count")
        }

    }

}
