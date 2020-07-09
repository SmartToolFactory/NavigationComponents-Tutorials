package com.smarttoolfactory.tutorial8_2dynamicfeatures_complexarchitecture

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.smarttoolfactory.tutorial8_2dynamicfeatures_complexarchitecture.databinding.ActivityMainBinding
import com.smarttoolfactory.tutorial8_2dynamicfeatures_complexarchitecture.fragment.factory.NavHostFragmentFactory

/**
 *
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        supportFragmentManager.fragmentFactory = NavHostFragmentFactory.create()
        super.onCreate(savedInstanceState)
        val dataBinding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)

    }
}