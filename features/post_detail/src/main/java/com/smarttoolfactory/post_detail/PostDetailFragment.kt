package com.smarttoolfactory.post_detail

import android.os.Bundle
import android.view.View
import com.smarttoolfactory.post_detail.databinding.FragmentPostDetailBinding
import com.smarttoolfactory.tutorial8_2dynamicfeatures_complexarchitecture.api.Post
import com.smarttoolfactory.tutorial8_2dynamicfeatures_complexarchitecture.fragment.blankfragment.BaseDataBindingFragment

class PostDetailFragment : BaseDataBindingFragment<FragmentPostDetailBinding>() {
    override fun getLayoutRes(): Int = R.layout.fragment_post_detail

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Get RepoListItem from navigation component arguments
        val post = arguments?.get("post") as Post?

        dataBinding?.item = post
    }
}