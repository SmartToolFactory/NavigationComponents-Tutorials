package com.smarttoolfactory.tutorial5_4navigationui_memoryleakcheck

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.smarttoolfactory.tutorial5_4navigationui_memoryleakcheck.databinding.ActivityMainBinding
import com.smarttoolfactory.tutorial5_4navigationui_memoryleakcheck.fragment.blankfragment.LoginFragment1
import com.smarttoolfactory.tutorial5_4navigationui_memoryleakcheck.fragment.blankfragment.LoginFragment2
import com.smarttoolfactory.tutorial5_4navigationui_memoryleakcheck.fragment.main.MainFragment


/**
 * Example to test if NavigationExtensions leak when fragment [BottomNavigationView] is replaced
 * with another fragment using
 * `requireActivity().findNavController().navigate`
 *
 * * If you select [LoginFragment1] and navigate to [LoginFragment2] using button with title
 * Login Via Main Navigation or via `requireActivity().findNavController().navigate`
 * causes multiple **Memory Leaks** after changing [MainFragment] with [LoginFragment2]
 *
 * ## Navigation extension work around leaks by default when the fragment BottomNavigationView is in replaced with another fragment
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val dataBinding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)
    }
}