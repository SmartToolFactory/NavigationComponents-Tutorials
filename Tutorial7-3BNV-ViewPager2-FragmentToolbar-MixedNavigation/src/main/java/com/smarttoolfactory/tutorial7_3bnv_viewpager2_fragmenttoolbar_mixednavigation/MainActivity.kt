package com.smarttoolfactory.tutorial7_3bnv_viewpager2_fragmenttoolbar_mixednavigation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.smarttoolfactory.tutorial7_3bnv_viewpager2_fragmenttoolbar_mixednavigation.fragment.blankfragment.PostStaggeredFragment
import com.smarttoolfactory.tutorial7_3bnv_viewpager2_fragmenttoolbar_mixednavigation.fragment.main.MainFragment


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
 * * [PostStaggeredFragment] calls snippet below to get main [NavController]
 * to navigate in main navigation graph
 * requireActivity().findNavController(R.id.main_nav_host_fragment).navigate(R.id.action_mainFragment_to_postDetailFragment, bundle)`
 *
 *
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
}