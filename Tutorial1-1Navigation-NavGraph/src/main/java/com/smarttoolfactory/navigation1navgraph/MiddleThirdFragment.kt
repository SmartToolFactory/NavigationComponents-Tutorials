package com.smarttoolfactory.navigation1navgraph


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.smarttoolfactory.navigationworkmanagerpagination.R

/**
 * A simple [Fragment] subclass.
 *
 */
class MiddleThirdFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_middle_3, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.btnNavigateHome).setOnClickListener {

            // Navigates to MainFragment
            findNavController().navigate(R.id.action_middle3_dest_to_mainFragment)

        }
    }

}
