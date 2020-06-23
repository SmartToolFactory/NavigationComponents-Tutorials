package com.smarttoolfactory.tutorial6_4_navigationui_viewpager_fragmenttoolbar

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/**
 *  In this example each [NavHostFragment] has it's own toolbar, also activity has
 *  it's own toolbar either.
 *
 *  Fragments
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}