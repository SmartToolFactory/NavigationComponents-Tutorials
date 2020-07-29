package com.smarttoolfactory.tutorial7_3bnv_viewpager2_fragmenttoolbar_mixednavigation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.smarttoolfactory.tutorial7_3bnv_viewpager2_fragmenttoolbar_mixednavigation.fragment.blankfragment.PostDetailFragment
import com.smarttoolfactory.tutorial7_3bnv_viewpager2_fragmenttoolbar_mixednavigation.fragment.blankfragment.PostGridFragment
import com.smarttoolfactory.tutorial7_3bnv_viewpager2_fragmenttoolbar_mixednavigation.fragment.blankfragment.PostStaggeredFragment
import com.smarttoolfactory.tutorial7_3bnv_viewpager2_fragmenttoolbar_mixednavigation.fragment.main.MainFragment
import com.smarttoolfactory.tutorial7_3bnv_viewpager2_fragmenttoolbar_mixednavigation.fragment.viewpagerfragment.ViewPagerContainerFragment


/*
   *** Navigation Architecture ***

     MainActivity
       |- MainNavHostFragment
            |
            |- ContainerNavHostFragment(BottomNavigationView  Appbar + Toolbar)
            |    |
            |    |- ViewPagerContainerFragment(ViewPager2 + TabLayout)
            |    |      |
            |    |      |- PostVerticalNavHost
            |    |      |  |- PostVerticalFragment -> PostDetailFragment
            |    |      |
            |    |      |- PostHorizontalNavHost
            |    |      |  |- PostHorizontalFragment -> PostDetailFragment
            |    |      |
            |    |      |- PostGridNavHostFragment
            |    |      |  |- PostGridFragment
            |    |      |
            |    |      |- PostStaggerNavHostFragment
            |    |      |  |- PostStaggeredFragment
            |    |      |
            |    |      |- NotificationHostFragment
            |    |      |  |- NF1 -> NF2 -> NF3
            |    |      |
            |    |      |- LoginFragment1
            |    |
            |    |
            |    |- DashboardNavHostFragment
            |    |   |- DF1 -> DF2 -> DF3
            |    |
            |    |- NotificationHostFragment
            |    |  |- NF1 -> NF2 -> NF3
            |    |
            |    |- &PostGridFragment -> PostDetailFragment
            |    |
            |    |- &LoginFragment1 -> LoginFragment2
            |
            |
            |- &PostStaggeredFragment -> PostDetailFragment

 */


/**
 * In this example [BottomNavigationView] is inside [MainFragment]. [MainActivity] can navigate to
 * a different fragment other than the [MainFragment] using nav_graph main destinations.
 *
 * * Navigation in this example is layered, fragments annotated with **&** display that
 * they navigate at that level, not actually added to that hierarchy.
 *
 * * [PostStaggeredFragment] which is in [ViewPager2] calls snippet below to get main [NavController]
 * to navigate in main navigation graph
 * requireActivity().findNavController(R.id.main_nav_host_fragment).navigate(R.id.action_mainFragment_to_postDetailFragment, bundle)`
 *
 * * [PostGridFragment] which is in [ViewPager2] gets [NavController] that belong to [ViewPagerContainerFragment] via
 * `parentFragment?.parentFragment?.findNavController()` and navigates from [ViewPagerContainerFragment] to [PostDetailFragment]
 *
 * * 🔥🔥🔥 If you navigate from [PostStaggeredFragment] to [PostDetailFragment] fragment you will see that memory leak occurs. It happens
 * due to [NavigationsionsExtensions] leaking because of listeners NOT being unregistered in extension functions.
 *
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
}