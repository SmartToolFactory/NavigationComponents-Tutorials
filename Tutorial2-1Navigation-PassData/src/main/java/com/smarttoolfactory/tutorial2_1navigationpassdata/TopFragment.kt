package com.smarttoolfactory.tutorial2_1navigationpassdata


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.smarttoolfactory.tutorial2_1navigationpassdata.R

/**
 * A simple [Fragment] subclass that gets passed data via arguments
 *
 */
class TopFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_top, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tvTop = view.findViewById<TextView>(R.id.tvTop)

        val myArg = arguments?.getInt("myArg")

        tvTop.text = "From Bundle myArg $myArg"
    }
}
