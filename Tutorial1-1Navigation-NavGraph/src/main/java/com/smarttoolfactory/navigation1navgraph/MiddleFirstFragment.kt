package com.smarttoolfactory.navigation1navgraph


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.smarttoolfactory.navigationworkmanagerpagination.R

/**
 * A simple [Fragment] subclass.
 *
 */
class MiddleFirstFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_middle_1, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Go Next
        view.findViewById<Button>(R.id.btn_destB_go_next).setOnClickListener {
            findNavController().navigate(R.id.action_middleFirstFragment_to_middleSecondFragment)
        }

        // Go Back
        view.findViewById<Button>(R.id.btn_destB_go_back).setOnClickListener {
            findNavController().popBackStack()
        }

    }


}
