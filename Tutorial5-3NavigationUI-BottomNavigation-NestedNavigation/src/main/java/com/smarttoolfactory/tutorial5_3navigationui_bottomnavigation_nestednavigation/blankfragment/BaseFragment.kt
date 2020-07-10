package com.smarttoolfactory.tutorial5_3navigationui_bottomnavigation_nestednavigation.blankfragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment

/**
 * Base fragment with data binding and prints lifecycle events
 *
 * LifeCycle of Fragments
 *
 * * onAttach()
 * * onCreate()
 * * onCreateView() -> View is created or Fragment returned from back stack
 * * onViewCreated()
 * * onStart()
 * * onResume()
 * * onPause()
 * * onStop()
 * * onDestroyView() fragment sent to back stack / Back navigation -> onCreateView() is called
 * * onDestroy()
 * * onDetach()
 */
abstract class BaseFragment : Fragment() {


    @LayoutRes
    abstract fun getLayoutRes(): Int

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
//        println("🔥 ${this.javaClass.simpleName} #${this.hashCode()}  onActivityCreated()")

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
//        println("🥰 ${this.javaClass.simpleName} #${this.hashCode()}   onAttach()")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        println("😀 ${this.javaClass.simpleName} #${this.hashCode()}  onCreate()")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

//        println("🤣 ${this.javaClass.simpleName} #${this.hashCode()} onCreateView()")

        // Inflate the layout for this fragment
        return inflater.inflate(getLayoutRes(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        println("🤩 BaseDataBindingFragment onViewCreated() view: $view")
    }


    override fun onAttachFragment(childFragment: Fragment) {
        super.onAttachFragment(childFragment)
//        println("😱✅ ${this.javaClass.simpleName} #${this.hashCode()}  onAttachFragment(), CHILD: $childFragment")
    }

    override fun onDestroyView() {
        super.onDestroyView()
//        println("🥵 ${this.javaClass.simpleName} #${this.hashCode()}  onDestroyView()")
    }

    override fun onDestroy() {
        super.onDestroy()
//        println("🥶 ${this.javaClass.simpleName} #${this.hashCode()}  onDestroy()")
    }

    override fun onDetach() {
        super.onDetach()
//        println("💀 BaseDataBindingFragment onDetach() $this")
    }

    override fun onResume() {
        super.onResume()
//        println("🎃 ${this.javaClass.simpleName} #${this.hashCode()} onResume()")
    }

    override fun onPause() {
        super.onPause()
//        println("😱 ${this.javaClass.simpleName} #${this.hashCode()} onPause()")
    }

}