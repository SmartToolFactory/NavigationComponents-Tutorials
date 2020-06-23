package com.smarttoolfactory.tutorial0_materialdesign.fragment_factory

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.smarttoolfactory.tutorial0_materialdesign.blankfragment.GenericFragmentParent
import com.smarttoolfactory.tutorial0_materialdesign.blankfragment.GenericFragmentWithArgs

class GenericFragmentFactory private constructor() : FragmentFactory() {

    var fragID = 0

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {

        println("🏭 GenericFragmentFactory instantiate() className: $className")

        return when (className) {
            GenericFragmentWithArgs::class.java.name -> GenericFragmentWithArgs(fragID)
            GenericFragmentParent::class.java.name -> GenericFragmentParent(fragID)
            else -> super.instantiate(classLoader, className)
        }

    }

    /**
     * This is for displaying purposes, [FragmentFactory] can be injected via DI or
     * set to SupportFragmentManager as factory
     */
    companion object {
        @JvmStatic
        fun getFragmentFactory(): GenericFragmentFactory {
            return GenericFragmentFactory()

        }

    }

}


