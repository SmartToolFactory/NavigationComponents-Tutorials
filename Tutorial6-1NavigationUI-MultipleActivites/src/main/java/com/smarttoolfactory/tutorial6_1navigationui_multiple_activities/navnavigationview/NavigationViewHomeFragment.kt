package com.smarttoolfactory.tutorial6_1navigationui_multiple_activities.navnavigationview


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.smarttoolfactory.tutorial6_1navigationui_multiple_activities.R


/**
 * A simple [Fragment] subclass.
 *
 */
class NavigationViewHomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }


}
