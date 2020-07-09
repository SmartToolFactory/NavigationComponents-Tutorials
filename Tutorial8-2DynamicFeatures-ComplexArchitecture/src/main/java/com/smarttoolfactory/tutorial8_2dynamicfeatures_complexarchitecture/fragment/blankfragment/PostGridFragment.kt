package com.smarttoolfactory.tutorial8_2dynamicfeatures_complexarchitecture.fragment.blankfragment

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.smarttoolfactory.tutorial8_2dynamicfeatures_complexarchitecture.R
import com.smarttoolfactory.tutorial8_2dynamicfeatures_complexarchitecture.adapter.PostListAdapter
import com.smarttoolfactory.tutorial8_2dynamicfeatures_complexarchitecture.databinding.FragmentPostListGridBinding
import com.smarttoolfactory.tutorial8_2dynamicfeatures_complexarchitecture.viewmodel.PostsCoroutineViewModel

class PostGridFragment : BaseDataBindingFragment<FragmentPostListGridBinding>() {


    override fun getLayoutRes(): Int = R.layout.fragment_post_list_grid

    private val viewModel by viewModels<PostsCoroutineViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.getPosts()

        println("🔥 PostGridFragment navController: ${findNavController()}")
        println("🔥 PostGridFragment parentFragment navController: ${parentFragment?.findNavController()}")
        println("🔥 PostGridFragment parent parentFragment navController: ${parentFragment?.parentFragment?.findNavController()}")


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

        viewModel.goToDetailScreen.observe(viewLifecycleOwner, Observer {

            it.getContentIfNotHandled()?.let { post ->
                val bundle = bundleOf("post" to post)

                /**
                 * This is the navController belong to ViewPagerContainerFragment
                 */
                try {

                    parentFragment?.parentFragment?.findNavController()
                        ?.navigate(R.id.action_view_pager_dest_to_postDetailFragment, bundle)
                } catch (e: Exception) {
                    e.printStackTrace()
                }

            }
        })

    }
}