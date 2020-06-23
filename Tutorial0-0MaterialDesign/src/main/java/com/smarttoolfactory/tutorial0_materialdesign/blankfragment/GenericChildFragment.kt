package com.smarttoolfactory.tutorial0_materialdesign.blankfragment

import android.os.Bundle
import android.view.View
import com.smarttoolfactory.tutorial0_materialdesign.R
import com.smarttoolfactory.tutorial0_materialdesign.databinding.FragmentGenericChildBinding


class GenericChildFragment : BaseDataBindingFragment<FragmentGenericChildBinding>() {

    override fun getLayoutRes(): Int {
        return R.layout.fragment_generic_child
    }

    private val fragmentName = this.javaClass.simpleName

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        println("🔥 $fragmentName onViewCreated() this: $this")
        dataBinding.tvTitle.text = "$fragmentName"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        println("👻 $fragmentName onDestroyView() this: $this")
    }

    override fun onDestroy() {
        super.onDestroy()
        println("💀 $fragmentName onDestroy() id: this: $this")
    }

}
