package com.smarttoolfactory.tutorial0_materialdesign.blankfragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.smarttoolfactory.tutorial0_materialdesign.R
import com.smarttoolfactory.tutorial0_materialdesign.databinding.FragmentHome1Binding


class HomeFragment1 : BaseDataBindingFragment<FragmentHome1Binding>() {

    override fun getLayoutRes(): Int {
        return R.layout.fragment_home1
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        Toast.makeText(activity, "HomeFragment1 onViewCreated() this: $this", Toast.LENGTH_SHORT)
            .show()

        dataBinding.btnNextPage.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
        }
    }

}
