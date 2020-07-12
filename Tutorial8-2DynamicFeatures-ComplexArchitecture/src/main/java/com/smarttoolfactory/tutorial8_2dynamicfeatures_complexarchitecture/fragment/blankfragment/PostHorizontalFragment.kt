package com.smarttoolfactory.tutorial8_2dynamicfeatures_complexarchitecture.fragment.blankfragment

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.smarttoolfactory.tutorial8_2dynamicfeatures_complexarchitecture.R
import com.smarttoolfactory.tutorial8_2dynamicfeatures_complexarchitecture.adapter.PostListAdapter
import com.smarttoolfactory.tutorial8_2dynamicfeatures_complexarchitecture.databinding.FragmentPostListHorizontalBinding
import com.smarttoolfactory.tutorial8_2dynamicfeatures_complexarchitecture.viewmodel.PostsCoroutineViewModel

class PostHorizontalFragment : BaseDataBindingFragment<FragmentPostListHorizontalBinding>() {
    override fun getLayoutRes(): Int = R.layout.fragment_post_list_horizontal


    private val viewModel by viewModels<PostsCoroutineViewModel>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindViews()
    }

    private fun bindViews() {

        val binding = dataBinding!!

        binding.recyclerView1.apply {
            layoutManager = LinearLayoutManager(requireContext())
        }

        // 🔥 Set lifecycle for data binding
        binding.lifecycleOwner = viewLifecycleOwner

        binding.viewModel = viewModel

        binding.recyclerView1.apply {

            // Set Layout manager
            this.layoutManager =
                LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)

            // Set RecyclerViewAdapter
            this.adapter =
                PostListAdapter(R.layout.row_post_horizontal, viewModel::onClick)
        }


        binding.recyclerView2.apply {

            // Set Layout manager
            this.layoutManager =
                LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)

            // Set RecyclerViewAdapter
            this.adapter =
                PostListAdapter(R.layout.row_post_horizontal, viewModel::onClick)
        }


        viewModel.getPosts()

        subscribeGoToDetailScreen()

    }

    private fun subscribeGoToDetailScreen() {

        viewModel.goToDetailScreen.observe(viewLifecycleOwner, Observer {

            it.getContentIfNotHandled()?.let { post ->
                val bundle = bundleOf("post" to post)
                findNavController().navigate(R.id.action_horizontalPostFragment_to_postDetailFragment, bundle)
            }
        })

    }
}