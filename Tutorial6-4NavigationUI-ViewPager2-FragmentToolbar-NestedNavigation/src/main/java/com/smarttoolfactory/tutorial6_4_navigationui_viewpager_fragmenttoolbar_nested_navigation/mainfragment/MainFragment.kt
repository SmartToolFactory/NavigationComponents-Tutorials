package com.smarttoolfactory.tutorial6_4_navigationui_viewpager_fragmenttoolbar_nested_navigation.mainfragment


import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import androidx.viewpager2.widget.ViewPager2
import com.smarttoolfactory.tutorial6_4_navigationui_viewpager_fragmenttoolbar.R
import com.smarttoolfactory.tutorial6_4_navigationui_viewpager_fragmenttoolbar.databinding.FragmentMainBinding
import com.smarttoolfactory.tutorial6_4_navigationui_viewpager_fragmenttoolbar_nested_navigation.adapter.ChildFragmentStateAdapter
import com.smarttoolfactory.tutorial6_4_navigationui_viewpager_fragmenttoolbar_nested_navigation.blankfragment.BaseDataBindingFragment


class MainFragment : BaseDataBindingFragment<FragmentMainBinding>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        // ViewPager2
        val viewPager = dataBinding.viewPager

        /*
            🔥 Set Adapter for ViewPager inside this fragment using this Fragment,
            more specifically childFragmentManager as param
         */
        viewPager.adapter = ChildFragmentStateAdapter(this)

        // FIXME Toolbar display for fragment for is not correct

      val itemCount =  viewPager!!.adapter!!.itemCount

        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {

            override fun onPageSelected(position: Int) {

                if (position == 3) {
                    dataBinding.appbar.visibility = View.VISIBLE
                } else {
                    dataBinding.appbar.visibility = View.INVISIBLE
                }
            }

        })

        val appBarConfig = AppBarConfiguration(findNavController().graph)
        dataBinding.toolbar.setupWithNavController(findNavController(), appBarConfig)


    }

    override fun getLayoutRes(): Int = R.layout.fragment_main


}