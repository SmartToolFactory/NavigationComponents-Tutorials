package com.smarttoolfactory.tutorial8_2dynamicfeatures_complexarchitecture

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.smarttoolfactory.tutorial8_2dynamicfeatures_complexarchitecture.fragment.factory.NavHostFragmentFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        supportFragmentManager.fragmentFactory = NavHostFragmentFactory.create()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}