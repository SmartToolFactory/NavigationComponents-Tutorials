package com.smarttoolfactory.navigation1navgraph


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import com.smarttoolfactory.navigationworkmanagerpagination.R


class MainFragment : Fragment() {

    lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val buttonTop = view.findViewById<Button>(R.id.btn_destA)

        /*
            Navigation to Destinations which are specified with app:destination in <action/> tag
         */
        // 🔥 Alternative 1 Only works this way not with LAMBDA
        buttonTop?.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.top_dest, null))

//        buttonA?.setOnClickListener {
//            // 🔥 Alternative 2
//            findNavController().navigate(R.id.top_dest, null)
//            // 🔥 Alternative 3
////            Navigation.findNavController(view).navigate(R.id.flow_step_one_dest)
//        }

        val buttonCenter = view.findViewById<Button>(R.id.btn_destB)

        // 🔥 Alternative 2
        buttonCenter?.setOnClickListener {

            val options = navOptions {
                anim {
                    enter = R.anim.slide_in_right
                    exit = R.anim.slide_out_left
                    popEnter = R.anim.slide_in_left
                    popExit = R.anim.slide_out_right
                }
            }

            findNavController().navigate(R.id.middle1_dest, null, options)
        }

        val buttonBottom = view.findViewById<Button>(R.id.btn_destC)
        buttonBottom?.setOnClickListener {
            // 🔥 Alternative 3
            Navigation.findNavController(view).navigate(R.id.bottom_dest)
        }

    }


}
