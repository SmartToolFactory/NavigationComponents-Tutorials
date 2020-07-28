package com.smarttoolfactory.tutorial6_5navigationui_viewpager2_fragmenttoolbar_mixednavigation

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.smarttoolfactory.tutorial6_5navigationui_viewpager2_fragmenttoolbar_mixednavigation.blankfragment.LoginFragment2
import com.smarttoolfactory.tutorial6_5navigationui_viewpager2_fragmenttoolbar_mixednavigation.navhost.ParentNavHostFragment
import com.smarttoolfactory.tutorial6_5navigationui_viewpager2_fragmenttoolbar_mixednavigation.viewmodel.AppbarViewModel
import com.smarttoolfactory.tutorial6_5navigationui_viewpager2_fragmenttoolbar_mixednavigation.viewpagerfragment.ViewPagerContainerFragment


/*
   *** Navigation Architecture ***

     MainActivity
        |- MainNavHost
           |
           |- ParenNavHost((Appbar + Toolbar)
               |
               |- ViewPagerContainerFragment(ViewPager2)
               |   |
               |   |- HomeNavHostFragment(Appbar + Toolbar)
               |   |  |- HF1 -> HF2 -> HF3
               |   |
               |   |- DashboardNavHostFragment(Appbar + Toolbar)
               |   |  |- DF1 -> DF2 -> DF3
               |   |
               |   |- NotificationHostFragment(Appbar + Toolbar)
               |   |  |- NF1 -> NF2 -> NF3
               |   |
               |   |-LoginFragment1
               |
               |- LoginFragment1 -> LoginFragment2

 */

/**
 *  In this example each [NavHostFragment] has it's own toolbar, also [ParentNavHostFragment] has
 *  it's own toolbar either.
 *
 *  * [LoginFragment2] in this example is added to  back stack of [ParentNavHostFragment]
 *  because of that it does not have any association with toolbar in  [ViewPagerContainerFragment]
 *
 *  * [ParentNavHostFragment]'s role is to have it's own Appbar to contain login fragments and
 *  navigate through them using Appbar. Without [ParentNavHostFragment] we navigate
 *  to [LoginFragment2] that has no Appbar.
 *
 *  * Visibility of [ParentNavHostFragment] is changed via liveData of [AppbarViewModel]
 *
 *  * However, there is an issue with setting visibility and dimensions of [ViewPager2] when
 *  visibility of Appbar of parent changes
 *
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listenBackStackChange()

    }

    private fun listenBackStackChange() {
        // Get NavHostFragment
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment)

        // ChildFragmentManager of NavHostFragment
        val navHostChildFragmentManager = navHostFragment?.childFragmentManager

        navHostChildFragmentManager?.addOnBackStackChangedListener {

            val backStackEntryCount = navHostChildFragmentManager.backStackEntryCount
            val fragments = navHostChildFragmentManager.fragments
            val fragmentCount = fragments.size

            println("🎃 Main graph backStackEntryCount: $backStackEntryCount, fragmentCount: $fragmentCount, fragments: $fragments")

            Toast.makeText(
                this,
                "🎃 Main graph backStackEntryCount: $backStackEntryCount, fragmentCount: $fragmentCount, fragments: $fragments",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}