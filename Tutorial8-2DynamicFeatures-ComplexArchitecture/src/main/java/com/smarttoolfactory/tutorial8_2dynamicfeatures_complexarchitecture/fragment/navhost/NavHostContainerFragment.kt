package com.smarttoolfactory.tutorial8_2dynamicfeatures_complexarchitecture.fragment.navhost

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.smarttoolfactory.tutorial8_2dynamicfeatures_complexarchitecture.R
import com.smarttoolfactory.tutorial8_2dynamicfeatures_complexarchitecture.util.Event
import com.smarttoolfactory.tutorial8_2dynamicfeatures_complexarchitecture.viewmodel.NavControllerViewModel
import kotlin.reflect.KProperty

/**
 * Fragment created via layout resource that belong to a layout that contains a [NavHostFragment]
 *
 * Requires a [FragmentFactory] to be able to create this fragment which does not posses
 * an empty constructor.
 *
 */
class NavHostContainerFragment(
    @LayoutRes private val layoutRes: Int,
    @IdRes private val navHostFragmentId: Int
) : Fragment() {

    private val navControllerViewModel by activityViewModels<NavControllerViewModel>()

    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val dataBinding: ViewDataBinding = DataBindingUtil.inflate(
            inflater, layoutRes, container, false
        )

        val nestedNavHostFragment =
            childFragmentManager.findFragmentById(navHostFragmentId) as? NavHostFragment

        if (nestedNavHostFragment?.navController == null)
            throw RuntimeException("This fragment should have nav host with NavController")

        navController = nestedNavHostFragment.navController

        return dataBinding.run {
            lifecycleOwner = viewLifecycleOwner
            root
        }
    }

    override fun onResume() {
        super.onResume()
        // Set this navController as ViewModel's navController
        navControllerViewModel.currentNavController.value = Event(navController)
    }

}

var NavHostFragment.viewModel: NavControllerViewModel by FieldProperty {
    NavControllerViewModel()
}


class FieldProperty<R, T : Any>(
    val initializer: (R) -> T = { throw IllegalStateException("Not initialized.") }
) {
    private val map = HashMap<R, T>()

    operator fun getValue(thisRef: R, property: KProperty<*>): T =
        map[thisRef] ?: setValue(thisRef, property, initializer(thisRef))

    operator fun setValue(thisRef: R, property: KProperty<*>, value: T): T {
        map[thisRef] = value
        return value
    }
}