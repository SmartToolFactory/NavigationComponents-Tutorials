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
import com.smarttoolfactory.tutorial8_2dynamicfeatures_complexarchitecture.databinding.FragmentPostListVerticalBinding
import com.smarttoolfactory.tutorial8_2dynamicfeatures_complexarchitecture.viewmodel.PostsCoroutineViewModel

class PostVerticalFragment : BaseDataBindingFragment<FragmentPostListVerticalBinding>() {


    override fun getLayoutRes(): Int = R.layout.fragment_post_list_vertical

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
                LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)

            // Set RecyclerViewAdapter
            this.adapter =
                PostListAdapter(R.layout.row_post_vertical, viewModel::onClick)
        }

        subscribeGoToDetailScreen()

    }

    private fun subscribeGoToDetailScreen() {

        viewModel.goToDetailScreen.observe(viewLifecycleOwner, Observer {

            it.getContentIfNotHandled()?.let { post ->
                val bundle = bundleOf("post" to post)
            findNavController().navigate(R.id.action_verticalPostFragment_to_postDetailFragment, bundle)
            }
        })

    }
}