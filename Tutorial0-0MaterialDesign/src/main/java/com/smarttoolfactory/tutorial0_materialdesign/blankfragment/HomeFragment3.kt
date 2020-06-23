package com.smarttoolfactory.tutorial0_materialdesign.blankfragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.smarttoolfactory.tutorial0_materialdesign.R
import com.smarttoolfactory.tutorial0_materialdesign.databinding.FragmentHome3Binding


class HomeFragment3 : BaseDataBindingFragment<FragmentHome3Binding>() {

    override fun getLayoutRes(): Int {
        return R.layout.fragment_home3
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Toast.makeText(activity, "HomeFragment3 onViewCreated() this: $this", Toast.LENGTH_SHORT)
            .show()
        println("🔥 HomeFragment3 onViewCreated() this: $this")
    }

}
