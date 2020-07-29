package com.smarttoolfactory.tutorial8_2dynamicfeatures_complexarchitecture

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.dynamicfeatures.fragment.DynamicNavHostFragment
import androidx.navigation.fragment.NavHostFragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.smarttoolfactory.tutorial8_2dynamicfeatures_complexarchitecture.databinding.ActivityMainBinding
import com.smarttoolfactory.tutorial8_2dynamicfeatures_complexarchitecture.fragment.main.MainFragment
import com.smarttoolfactory.tutorial8_2dynamicfeatures_complexarchitecture.fragment.main.MainFragmentWithViewPager


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
           |    |
           |    |- NF1 | 2F2 -> Camera Dynamic Feature
           |
           |
           |- DF1 | 2F2 -> Photo Dynamic Feature
           |
           |- NF1 | 2F2 -> Camera Dynamic Feature
           |
           |- &PostStaggeredFragment -> PostDetailFragment

           // Photos Dynamic feature navigate to Camera Dynamic feature
           |- Photo Dynamic Feature -> Camera Dynamic Feature
 */

/*
    With MainFragment

    🔥 ViewPagerContainerFragment navController: androidx.navigation.NavHostController@b65d012
    🔥 PostGridFragment navController: androidx.navigation.NavHostController@8acb51d
    🔥 PostGridFragment parentFragment navController: androidx.navigation.NavHostController@8acb51d
    🔥 PostGridFragment parent parentFragment navController: androidx.navigation.NavHostController@b65d012
    🔥 LoginFragment1 navController: androidx.navigation.NavHostController@b65d012
 */

/*
    With MainFragmentWithPager

    🔥 MainFragmentWithViewPager navController: androidx.navigation.NavHostController@c575eec
    🔥 ViewPagerContainerFragment navController: androidx.navigation.NavHostController@c575eec
    🔥 PostGridFragment navController: androidx.navigation.NavHostController@ec3d764
    🔥 PostGridFragment parentFragment navController: androidx.navigation.NavHostController@ec3d764
    🔥 PostGridFragment parent parentFragment navController: androidx.navigation.NavHostController@c575eec
    🔥 LoginFragment1 navController: androidx.navigation.NavHostController@c575eec
 */

/**
 * In this example Camera and Photos dynamic features are navigable from Dashboard and Notification fragments.
 *
 * * There are two versions of main fragment.
 *
 * * One with [MainFragment] which uses [BottomNavigationView] extension, and [MainFragmentWithViewPager] uses [ViewPager2]
 * to create back stack of [BottomNavigationView].
 *
 * * 🔥 Since [DynamicNavHostFragment] used instead of [NavHostFragment] another extension should be written that creates
 * fragments that have [DynamicNavHostFragment] in fragments.
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        val dataBinding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)

    }
}