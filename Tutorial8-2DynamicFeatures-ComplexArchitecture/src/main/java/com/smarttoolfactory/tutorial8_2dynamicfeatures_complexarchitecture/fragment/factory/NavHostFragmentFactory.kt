package com.smarttoolfactory.tutorial8_2dynamicfeatures_complexarchitecture.fragment.factory

import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.smarttoolfactory.tutorial8_2dynamicfeatures_complexarchitecture.fragment.navhost.NavHostContainerFragment


// FIXME After rotation ViewPager2 creates the last fragment with id and, neighboring fragments generated same if not waited fo 10 seconds
// 🔥🔥🔥 It takes 10 seconds for ViewPager2 to destroy fragments created after rotation but not required by app

/**
 * Factory for creating fragments that contain NavHostFragment or DynamicNavHostFragment in
 * xml file for that layout.
 *
 * layoutRes is  R.layout.layoutRes of the fragment
 * navHostFragment is id of NavHostFragment or DynamicNavHostFragment in layout R.layout.layoutRes
 */
class NavHostFragmentFactory private constructor() : FragmentFactory() {

    @LayoutRes
    private var layoutRes: Int = -1

    @IdRes
    private var navHostFragmentId: Int = -1

    private var name: String = "NoName"

    fun createNavHostFragment(
        classLoader: ClassLoader,
        className: String,
        @LayoutRes layoutRes: Int,
        @IdRes navHostFragmentId: Int,
        name: String
    ): Fragment {

        this.layoutRes = layoutRes
        this.navHostFragmentId = navHostFragmentId
        this.name = name

        println("🔥🔥 NavHostFragment creteNavHostFragment() layoutRes: ${this.layoutRes}, navHostFragmentId: ${this.navHostFragmentId}, name: ${this.name}")

        return instantiate(classLoader, className)
    }


    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {

        println("⚠️ NavHostFragment instantiate() name: ${this.name}")

        return when (className) {


            NavHostContainerFragment::class.java.name -> {

                println("⚠️😱 NavHostFragment instantiate() layoutRes: ${this.layoutRes}, navHostFragmentId: ${this.navHostFragmentId}, name: ${this.name}")

                NavHostContainerFragment(
                    layoutRes,
                    navHostFragmentId
                )
            }
            else -> super.instantiate(classLoader, className)
        }
    }


    companion object {

        private val navHostFragmentFactory: NavHostFragmentFactory by lazy {
            println("NAVHOSTFRAGMENT FACTORY !!")
            NavHostFragmentFactory()
        }

        const val KEY_RES_ID = "key-res-id"
        const val KEY_NAV_HOST_ID = "key-nav-host-id"

        @JvmStatic
        fun create(): NavHostFragmentFactory {
            println("🏰 NavHostFragmentFactory navHostFragmentFactory: $navHostFragmentFactory")
            return navHostFragmentFactory
        }

    }

}