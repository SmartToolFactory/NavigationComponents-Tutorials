package com.smarttoolfactory.tutorial0_materialdesign.chapter2_1viewpager2_fragments

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.smarttoolfactory.tutorial0_materialdesign.R
import com.smarttoolfactory.tutorial0_materialdesign.blankfragment.FragmentThatContainsViewPager
import com.smarttoolfactory.tutorial0_materialdesign.databinding.Activity23viewpagerInFragmentBinding
import com.smarttoolfactory.tutorial0_materialdesign.fragment_factory.GenericFragmentFactory

class Activity2_3ViewPagerInsideFragment : AppCompatActivity() {

    lateinit var dataBinding: Activity23viewpagerInFragmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        /*
            ⚠️ FragmentFactory should be placed before super.onCreate to be able
            find non-empty constructors of fragments
         */
        supportFragmentManager.fragmentFactory = GenericFragmentFactory.getFragmentFactory()

        super.onCreate(savedInstanceState)
        dataBinding =
            DataBindingUtil.setContentView(this, R.layout.activity2_3viewpager_in_fragment)

        dataBinding.toolbar.title = "ViewPager Inside Fragment"

        setSupportActionBar(dataBinding.toolbar)

        createRootFragment()

        /*
            Add a back stack listener to check number of fragments to display top back button
         */
        supportFragmentManager.addOnBackStackChangedListener {

            val fragmentOnTop = supportFragmentManager.findFragmentById(
                R.id.fragment_container_view
            )

            val fragmentCount = supportFragmentManager.backStackEntryCount

            Toast.makeText(
                this@Activity2_3ViewPagerInsideFragment,
                // Check fragment count and fragment on top of the stack for fragment_container_view
                "Activity fragmentCount: $fragmentCount, current Frag: $fragmentOnTop",
                Toast.LENGTH_SHORT
            ).show()

            supportActionBar?.setDisplayHomeAsUpEnabled(fragmentCount > 0)

        }
    }

    /**
     * Create root fragment for this Activity
     */
    private fun createRootFragment() {

        // ✅ Replace Fragment with Java Style
        //        supportFragmentManager.beginTransaction()
        //            .replace(
        //                R.id.fragment_container_view,
        //                FragmentThatContainsViewPager::class.java,
        //                Bundle()
        //            )
        //            .commit()

        // ✅ Replace Fragment with DSL style
        supportFragmentManager.commit {
            replace<FragmentThatContainsViewPager>(R.id.fragment_container_view)
            // Using addToBackStack makes backStackEntryCount 1
//                .addToBackStack(null)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        supportFragmentManager.popBackStack()
        return true
    }

}