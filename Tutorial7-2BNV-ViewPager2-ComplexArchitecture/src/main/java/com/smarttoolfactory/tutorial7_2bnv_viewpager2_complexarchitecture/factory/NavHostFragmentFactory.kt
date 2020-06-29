package com.smarttoolfactory.tutorial7_2bnv_viewpager2_complexarchitecture.factory

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.smarttoolfactory.tutorial7_2bnv_viewpager2_complexarchitecture.navhost.BaseDataBindingNavHostFragment

class NavHostFragmentFactory private constructor() : FragmentFactory() {

    @LayoutRes
    private var layoutRes: Int = -1

    @IdRes
    private var navHostFragmentId: Int = -1


    fun createNavHostFragment(
        classLoader: ClassLoader,
        className: String,
        bundle: Bundle
    ): Fragment {

        layoutRes = bundle.getInt(KEY_RES_ID)
        navHostFragmentId = bundle.getInt(KEY_NAV_HOST_ID)

        return instantiate(classLoader, className)
    }

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {

        return when (className) {
            BaseDataBindingNavHostFragment::class.java.name -> BaseDataBindingNavHostFragment(
                layoutRes,
                navHostFragmentId
            )
            else -> super.instantiate(classLoader, className)
        }
    }


    companion object {

        const val KEY_RES_ID = "key-res-id"
        const val KEY_NAV_HOST_ID = "key-nav-host-id"

        @JvmStatic
        fun create(): NavHostFragmentFactory {
            return NavHostFragmentFactory()
        }

    }

}