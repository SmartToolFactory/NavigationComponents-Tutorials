package com.smarttoolfactory.tutorial7_3bnv_viewpager2_fragmenttoolbar_mixednavigation.fragment.blankfragment

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.smarttoolfactory.tutorial7_3bnv_viewpager2_fragmenttoolbar_mixednavigation.R
import com.smarttoolfactory.tutorial7_3bnv_viewpager2_fragmenttoolbar_mixednavigation.adapter.PostListAdapter
import com.smarttoolfactory.tutorial7_3bnv_viewpager2_fragmenttoolbar_mixednavigation.databinding.FragmentPostListStaggeredBinding
import com.smarttoolfactory.tutorial7_3bnv_viewpager2_fragmenttoolbar_mixednavigation.viewmodel.PostsCoroutineViewModel

class PostStaggeredFragment : BaseDataBindingFragment<FragmentPostListStaggeredBinding>() {


    override fun getLayoutRes(): Int = R.layout.fragment_post_list_staggered

    private val viewModel by viewModels<PostsCoroutineViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.getPosts()

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindViews()
    }

    private fun bindViews() {

        val binding = dataBinding!!

        // 🔥 Set lifecycle for data binding
        binding.lifecycleOwner = viewLifecycleOwner

        binding.viewModel = viewModel

        binding.recyclerView.apply {

            // Set Layout manager
            this.layoutManager =
                StaggeredGridLayoutManager(3, LinearLayoutManager.VERTICAL)

            // Set RecyclerViewAdapter
            this.adapter =
                PostListAdapter(R.layout.row_post_staggered, viewModel::onClick)
        }

        subscribeGoToDetailScreen()


    }

    private fun subscribeGoToDetailScreen() {

        viewModel.goToDetailScreen.observe(viewLifecycleOwner, Observer {

            it.getContentIfNotHandled()?.let { post ->
                val bundle = bundleOf("post" to post)

                /*
         🔥 This is navController we get from findNavController not the one required
         for navigating nested fragments
      */
                requireActivity().findNavController(R.id.mainNavHostFragment)
                    .navigate(R.id.action_mainFragment_to_postDetailFragment, bundle)

//                val mainNavController =
//                    Navigation.findNavController(requireActivity(), R.id.main_nav_host_fragment)
//                mainNavController.navigate(R.id.action_mainFragment_to_postDetailFragment, bundle)
            }
        })

    }


}