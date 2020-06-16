package com.smarttoolfactory.tutorial2_1navigationpassdata


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs


/**
 * A simple [Fragment] subclass. that contains safe args by [BottomFragmentArgs]
 *
 */
class BottomFragment : Fragment() {

    private val safeArgs: BottomFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bottom, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val tvBottom = view.findViewById<TextView>(R.id.tvBottom)
        val myData = safeArgs.myData

        tvBottom.text = "Name: ${myData?.name}, value: ${myData?.value}"

    }

}
