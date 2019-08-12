package com.smarttoolfactory.tutorial5_1navigationui_bottomnavigation.blankfragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.smarttoolfactory.tutorial5_1navigationui_bottomnavigation.R


class DetailFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val etData = view.findViewById<EditText>(R.id.etData)
        val tvData = view.findViewById<TextView>(R.id.tvData)


        val button = view.findViewById<Button>(R.id.btnAddData)

        button.setOnClickListener {
            tvData.text = etData?.text?.toString()
        }

        Toast.makeText(activity, "DetailFragment onViewCreated() this: $this", Toast.LENGTH_SHORT).show()
        println("🔥 DetailFragment onViewCreated() this: $this")
    }
}
