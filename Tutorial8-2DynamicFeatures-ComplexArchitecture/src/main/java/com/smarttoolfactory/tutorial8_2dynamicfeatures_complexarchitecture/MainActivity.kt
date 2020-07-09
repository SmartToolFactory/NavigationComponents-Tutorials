package com.smarttoolfactory.tutorial8_2dynamicfeatures_complexarchitecture

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.smarttoolfactory.tutorial8_2dynamicfeatures_complexarchitecture.databinding.ActivityMainBinding

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
 *
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        // Set FragmentFactory
//        supportFragmentManager.fragmentFactory = NavHostFragmentFactory.create()

        super.onCreate(savedInstanceState)
        val dataBinding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)

    }
}