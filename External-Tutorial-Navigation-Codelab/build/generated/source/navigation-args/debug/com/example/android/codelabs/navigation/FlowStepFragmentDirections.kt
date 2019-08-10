package com.example.android.codelabs.navigation

import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections

class FlowStepFragmentDirections private constructor() {
    companion object {
        fun nextAction(): NavDirections = ActionOnlyNavDirections(R.id.next_action)
    }
}
