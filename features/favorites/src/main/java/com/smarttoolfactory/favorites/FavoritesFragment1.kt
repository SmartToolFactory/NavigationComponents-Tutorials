package com.smarttoolfactory.favorites

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.smarttoolfactory.favorites.databinding.FragmentFavorites1Binding
import com.smarttoolfactory.tutorial8_1dynamicfeatures_navigation.fragment.blankfragment.BaseDataBindingFragment

class FavoritesFragment1 : BaseDataBindingFragment<FragmentFavorites1Binding>() {
    override fun getLayoutRes(): Int = R.layout.fragment_favorites1


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val count = arguments?.get("count") as Int

        dataBinding.tvInfo.text = "${dataBinding.tvInfo.text}\n Count: $count"

        dataBinding.btnNextPage.setOnClickListener {
            findNavController().navigate(R.id.action_favoritesFragment1_to_favoritesFragment2)
        }
    }
}