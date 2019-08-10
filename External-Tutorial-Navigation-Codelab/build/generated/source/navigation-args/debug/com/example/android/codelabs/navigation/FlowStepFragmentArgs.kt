package com.example.android.codelabs.navigation

import android.os.Bundle
import androidx.navigation.NavArgs
import kotlin.Int
import kotlin.jvm.JvmStatic

data class FlowStepFragmentArgs(val flowStepNumber: Int = 2) : NavArgs {
    fun toBundle(): Bundle {
        val result = Bundle()
        result.putInt("flowStepNumber", this.flowStepNumber)
        return result
    }

    companion object {
        @JvmStatic
        fun fromBundle(bundle: Bundle): FlowStepFragmentArgs {
            bundle.setClassLoader(FlowStepFragmentArgs::class.java.classLoader)
            val __flowStepNumber : Int
            if (bundle.containsKey("flowStepNumber")) {
                __flowStepNumber = bundle.getInt("flowStepNumber")
            } else {
                __flowStepNumber = 2
            }
            return FlowStepFragmentArgs(__flowStepNumber)
        }
    }
}
