package com.smarttoolfactory.tutorial1_2navigation_nestednavgraph.blankfragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

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
abstract class BaseDataBindingFragment<Binding : ViewBinding> : Fragment() {

    lateinit var dataBinding: Binding

    @LayoutRes
    abstract fun getLayoutRes(): Int

    override fun onAttach(context: Context) {
        super.onAttach(context)
        println("🥰 BaseDataBindingFragment onAttach() $this")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        println("😀 BaseDataBindingFragment onCreate() $this")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        println("🤣 BaseDataBindingFragment onCreateView() $this")
        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate(inflater, getLayoutRes(), container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        println("🤩 BaseDataBindingFragment onViewCreated() view: $view,  $this")
    }


    override fun onAttachFragment(childFragment: Fragment) {
        super.onAttachFragment(childFragment)
//        println("😱 BaseDataBindingFragment onAttachFragment() $this, CHILD: $childFragment")
    }

    override fun onDestroyView() {
        super.onDestroyView()
//        println("🥵 BaseDataBindingFragment onDestroyView() $this")
    }

    override fun onDestroy() {
        super.onDestroy()
//        println("🥶 BaseDataBindingFragment onDestroy() $this")
    }

    override fun onDetach() {
        super.onDetach()
//        println("💀 BaseDataBindingFragment onDetach() $this")
    }

}