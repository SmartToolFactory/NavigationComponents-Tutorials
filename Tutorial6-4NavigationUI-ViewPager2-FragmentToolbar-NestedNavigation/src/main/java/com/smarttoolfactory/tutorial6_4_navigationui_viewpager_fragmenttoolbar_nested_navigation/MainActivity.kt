package com.smarttoolfactory.tutorial6_4_navigationui_viewpager_fragmenttoolbar_nested_navigation

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.NavHostFragment
import com.smarttoolfactory.tutorial6_4_navigationui_viewpager_fragmenttoolbar.R
import com.smarttoolfactory.tutorial6_4_navigationui_viewpager_fragmenttoolbar.databinding.ActivityMainBinding
import com.smarttoolfactory.tutorial6_4_navigationui_viewpager_fragmenttoolbar_nested_navigation.mainfragment.MainFragment

/**
 *  In this example each [NavHostFragment] has it's own toolbar, also activity has
 *  it's own toolbar either.
 *
 *  * [LoginFragment2] in this example is added to main back stack instead of Navhost,
 *  because of that it does not have any association with toolbar in  [MainFragment]
 *
 *  * It creates a navigation with back arrow but since it's in [MainFragment] it's
 *  displayed for a moment(This is not a correct implementation only for demonstration)
 *  and [LoginFragment2] is opened afterwards with no toolbar
 *
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val dataBinding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)

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