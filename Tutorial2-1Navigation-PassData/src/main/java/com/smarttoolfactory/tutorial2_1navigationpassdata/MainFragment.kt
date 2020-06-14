package com.smarttoolfactory.tutorial2_1navigationpassdata


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.smarttoolfactory.tutorial2_1navigationpassdata.model.Data


class MainFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Top fragment gets argument normal way
        val buttonTop = view.findViewById<Button>(R.id.btn_destA)
        buttonTop?.setOnClickListener {

            val bundle = bundleOf("myArg" to 6)
            // 🔥 Alternative 2
            findNavController().navigate(R.id.action_mainFragment_to_topFragment, bundle)
        }


        val buttonBottom = view.findViewById<Button>(R.id.btn_destC)
        buttonBottom?.setOnClickListener {
            // 🔥 Alternative 3
//            Navigation.findNavController(view).navigate(R.id.bottom_dest)

            val myDataArg = Data("test", 4)
            // Create Action
            val action =
                MainFragmentDirections.actionMainFragmentToBottomFragment(
                    myDataArg
                )
            findNavController().navigate(action)
        }

    }


}
