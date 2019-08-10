package com.example.android.codelabs.navigation

import android.os.Bundle
import androidx.navigation.NavDirections
import kotlin.Int

class HomeFragmentDirections private constructor() {
    private data class NextAction(val flowStepNumber: Int = 1) : NavDirections {
        override fun getActionId(): Int = R.id.next_action

        override fun getArguments(): Bundle {
            val result = Bundle()
            result.putInt("flowStepNumber", this.flowStepNumber)
            return result
        }
    }

    companion object {
        fun nextAction(flowStepNumber: Int = 1): NavDirections = NextAction(flowStepNumber)
    }
}
