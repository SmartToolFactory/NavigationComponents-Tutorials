package com.smarttoolfactory.tutorial0_materialdesign.blankfragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.smarttoolfactory.tutorial0_materialdesign.R
import com.smarttoolfactory.tutorial0_materialdesign.databinding.FragmentGenericParentBinding


/**
 * Parent fragment that can go to detail fragment
 */
class GenericFragmentParent(private val fragID: Int) :
    BaseDataBindingFragment<FragmentGenericParentBinding>() {

    private val fragmentName = this.javaClass.simpleName

    override fun getLayoutRes(): Int {
        return R.layout.fragment_generic_parent
    }

    var count = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        println("🥶 $fragmentName onCreateView() id: $fragID, this: $this")
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        println("🔥 $fragmentName onViewCreated() id: $fragID, this: $this")

        dataBinding.tvLifeCycle.text = "$fragmentName onViewCreated() id: $fragID, this: $this"
        dataBinding.tvTitle.text = "$fragmentName#$fragID Count: ${count++}"

        dataBinding.btnIncrease.setOnClickListener {
            dataBinding.tvTitle.text = "$fragmentName#$fragID Count: ${count++}"

        }

        dataBinding.btnNextPage.setOnClickListener {

            /*
                🔥🔥⚠️ Replacing in container in Activity for both fragments to
                be in same stack of supportFragmentManager and
             */
            requireActivity().supportFragmentManager.commit {
                replace<GenericChildFragment>(R.id.fragment_container_view)
                    .addToBackStack(null)
            }
        }

    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

    }

    override fun onAttachFragment(childFragment: Fragment) {
        super.onAttachFragment(childFragment)
    }

    override fun onDetach() {
        super.onDetach()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        println("👻 $fragmentName onDestroyView() id: $fragID, this: $this")
    }

    override fun onDestroy() {
        super.onDestroy()
        println("💀 $fragmentName onDestroy() id: $fragID, this: $this")
    }


}
