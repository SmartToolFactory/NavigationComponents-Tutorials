package com.smarttoolfactory.tutorial0_materialdesign.blankfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.smarttoolfactory.tutorial0_materialdesign.R
import com.smarttoolfactory.tutorial0_materialdesign.databinding.FragmentGenericBinding


class GenericFragment : BaseDataBindingFragment<FragmentGenericBinding>() {

    override fun getLayoutRes(): Int {
        return R.layout.fragment_generic
    }
    
    private val fragmentName = this.javaClass.simpleName

    private var fragID: Int? = 0
    private var count = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        println("🥶 fragmentName onCreateView() id: $fragID, this: $this")
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fragID = arguments?.getInt(ARG_POSITION) ?: 0

        println("🔥 $fragmentName onViewCreated() id: $fragID, this: $this")

        dataBinding.tvLifeCycle.text =
            "$fragmentName  onViewCreated() id: $fragID, this: $this"
        dataBinding.tvTitle.text = "$fragmentName#$fragID Count: ${count++}"

        dataBinding.btnIncrease.setOnClickListener {
            dataBinding.tvTitle.text = "$fragmentName#$fragID Count: ${count++}"
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        println("👻 $fragmentName onDestroyView() id: $fragID, this: $this")
    }

    override fun onDestroy() {
        super.onDestroy()
        println("💀 $fragmentName onDestroy() id: $fragID, this: $this")
    }


    companion object {

        const val ARG_POSITION = "position"

        @JvmStatic
        fun newInstance(position: Int): Fragment {

            val bundle = Bundle().apply {
                putInt(ARG_POSITION, position)
            }

            return GenericFragment().apply {
                arguments = bundle
            }
        }
    }
}
