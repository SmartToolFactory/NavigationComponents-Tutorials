package com.smarttoolfactory.tutorial5_2navigationui_bottomnavigation_advanced.blankfragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.smarttoolfactory.tutorial5_2navigationui_bottomnavigation_advanced.R


class SettingsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        Toast.makeText(activity, "SettingsFragment onCreateView() this: $this", Toast.LENGTH_SHORT).show()

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Toast.makeText(activity!!,"SettingsFragment onViewCreated() this: $this", Toast.LENGTH_SHORT).show()
        println("🔥 SettingsFragment onViewCreated() this: $this")
    }

}
