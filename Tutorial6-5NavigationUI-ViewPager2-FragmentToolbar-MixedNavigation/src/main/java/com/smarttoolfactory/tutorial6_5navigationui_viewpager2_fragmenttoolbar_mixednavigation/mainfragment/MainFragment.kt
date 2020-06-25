package com.smarttoolfactory.tutorial6_5navigationui_viewpager2_fragmenttoolbar_mixednavigation.mainfragment


import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.navigation.navGraphViewModels
import androidx.viewpager2.widget.ViewPager2
import com.smarttoolfactory.tutorial6_5navigationui_viewpager2_fragmenttoolbar_mixednavigation.R
import com.smarttoolfactory.tutorial6_5navigationui_viewpager2_fragmenttoolbar_mixednavigation.adapter.ChildFragmentStateAdapter
import com.smarttoolfactory.tutorial6_5navigationui_viewpager2_fragmenttoolbar_mixednavigation.blankfragment.BaseDataBindingFragment
import com.smarttoolfactory.tutorial6_5navigationui_viewpager2_fragmenttoolbar_mixednavigation.databinding.FragmentMainBinding
import com.smarttoolfactory.tutorial6_5navigationui_viewpager2_fragmenttoolbar_mixednavigation.viewmodel.AppbarViewModel


class MainFragment : BaseDataBindingFragment<FragmentMainBinding>() {

    val viewModel by activityViewModels<AppbarViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // ViewPager2
        val viewPager = dataBinding.viewPager

        /*
            🔥 Set Adapter for ViewPager inside this fragment using this Fragment,
            more specifically childFragmentManager as param
         */
        val adapter = ChildFragmentStateAdapter(this)
        viewPager.adapter = adapter

        val itemCount = adapter.itemCount

        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {

            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                viewModel.appbarParentVisibility.value = position == itemCount - 1
            }
        })

    }

    override fun getLayoutRes(): Int = R.layout.fragment_main


}