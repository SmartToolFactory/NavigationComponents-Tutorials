package com.smarttoolfactory.tutorial7_3bnv_viewpager2_fragmenttoolbar_mixednavigation.blankfragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.smarttoolfactory.tutorial7_3bnv_viewpager2_fragmenttoolbar_mixednavigation.R
import com.smarttoolfactory.tutorial7_3bnv_viewpager2_fragmenttoolbar_mixednavigation.adapter.PostListAdapter
import com.smarttoolfactory.tutorial7_3bnv_viewpager2_fragmenttoolbar_mixednavigation.databinding.FragmentPostListGridBinding
import com.smarttoolfactory.tutorial7_3bnv_viewpager2_fragmenttoolbar_mixednavigation.viewmodel.PostsCoroutineViewModel

class PostGridFragment : BaseDataBindingFragment<FragmentPostListGridBinding>() {


    override fun getLayoutRes(): Int = R.layout.fragment_post_list_grid

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

        // 🔥 Set lifecycle for data binding
        dataBinding.lifecycleOwner = viewLifecycleOwner

        dataBinding.viewModel = viewModel

        dataBinding.recyclerView.apply {

            // Set Layout manager
            this.layoutManager =
                GridLayoutManager(requireContext(), 3)

            // Set RecyclerViewAdapter
            this.adapter =
                PostListAdapter(R.layout.row_post_grid, viewModel::onClick)
        }

        subscribeGoToDetailScreen()


    }

    private fun subscribeGoToDetailScreen() {

//        viewModel.goToDetailScreen.observe(this, Observer {
//
//            // Create Action
////            val action = RepoListFragmentDirections
////                .actionRepoListFragmentToRepoDetailFragment(it)
////
////            findNavController().navigate(action)
//
//            val bundle = bundleOf("repoItem" to it)
////            findNavController().navigate(R.id.action_repoListFragment_to_repoDetailFragment, bundle)
//        })
//
//    }


    }
}