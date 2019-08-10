package com.example.android.codelabs.navigation

import android.os.Bundle
import androidx.navigation.NavArgs
import java.lang.IllegalArgumentException
import kotlin.String
import kotlin.jvm.JvmStatic

data class DeepLinkFragmentArgs(val myarg: String = "Android!") : NavArgs {
    fun toBundle(): Bundle {
        val result = Bundle()
        result.putString("myarg", this.myarg)
        return result
    }

    companion object {
        @JvmStatic
        fun fromBundle(bundle: Bundle): DeepLinkFragmentArgs {
            bundle.setClassLoader(DeepLinkFragmentArgs::class.java.classLoader)
            val __myarg : String?
            if (bundle.containsKey("myarg")) {
                __myarg = bundle.getString("myarg")
                if (__myarg == null) {
                    throw IllegalArgumentException("Argument \"myarg\" is marked as non-null but was passed a null value.")
                }
            } else {
                __myarg = "Android!"
            }
            return DeepLinkFragmentArgs(__myarg)
        }
    }
}
