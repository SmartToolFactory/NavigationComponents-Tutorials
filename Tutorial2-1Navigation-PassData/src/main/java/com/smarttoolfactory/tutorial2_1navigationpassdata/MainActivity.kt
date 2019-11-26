package com.smarttoolfactory.tutorial2_1navigationpassdata

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

/**
 * Example for passing data between fragments via bundle and safe args.
 *
 * [TopFragment] uses [Bundle] while  [BottomFragment] uses safe args
 *
 * [BottomFragment] is navigated via MainFragmentDirections.actionMainFragmentToBottomFragment Action
 *
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.addOnBackStackChangedListener {
            val backStackEntryCount = supportFragmentManager.backStackEntryCount
            val fragments = supportFragmentManager.fragments


            Toast.makeText(
                this,
                "MainActivity() backStackEntryCount: $backStackEntryCount, fragments size: ${fragments.size}",
                Toast.LENGTH_SHORT
            ).show()

        }
    }

}

















