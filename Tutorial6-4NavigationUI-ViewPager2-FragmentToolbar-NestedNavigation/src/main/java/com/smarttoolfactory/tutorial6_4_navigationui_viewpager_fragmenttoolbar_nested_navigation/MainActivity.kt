package com.smarttoolfactory.tutorial6_4_navigationui_viewpager_fragmenttoolbar_nested_navigation

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.viewpager2.widget.ViewPager2
import com.smarttoolfactory.tutorial6_4_navigationui_viewpager_fragmenttoolbar.R
import com.smarttoolfactory.tutorial6_4_navigationui_viewpager_fragmenttoolbar.databinding.ActivityMainBinding
import com.smarttoolfactory.tutorial6_4_navigationui_viewpager_fragmenttoolbar_nested_navigation.adapter.ChildFragmentStateAdapter

/**
 *  In this example each [NavHostFragment] has it's own toolbar, also activity has
 *  it's own toolbar either.
 *
 *  Fragments
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val dataBinding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)
    }
}