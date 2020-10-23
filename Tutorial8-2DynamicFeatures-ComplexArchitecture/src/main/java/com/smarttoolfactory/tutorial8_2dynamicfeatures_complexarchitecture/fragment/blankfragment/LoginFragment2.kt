package com.smarttoolfactory.tutorial8_2dynamicfeatures_complexarchitecture.fragment.blankfragment


import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.appbar.AppBarLayout
import com.smarttoolfactory.tutorial8_2dynamicfeatures_complexarchitecture.MainActivity
import com.smarttoolfactory.tutorial8_2dynamicfeatures_complexarchitecture.R
import com.smarttoolfactory.tutorial8_2dynamicfeatures_complexarchitecture.databinding.FragmentLogin2Binding

class LoginFragment2 : BaseDataBindingFragment<FragmentLogin2Binding>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        Toast.makeText(requireContext(), "FragementA onCreateOptionsMenu()", Toast.LENGTH_SHORT).show()

        inflater.inflate(R.menu.menu_login_detail, menu)
        super.onCreateOptionsMenu(menu,inflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val toolbar = dataBinding!!. toolbar
        val appBarConfig = AppBarConfiguration(findNavController().graph)
        toolbar.setupWithNavController(findNavController(), appBarConfig)

        // Alternative 1 for setting Fragment Toolbar Options menu
//        (requireActivity() as? MainActivity)?.setSupportActionBar(toolbar)


        // Alternative 2 for setting Fragment Toolbar Options menu
        toolbar.inflateMenu(R.menu.menu_login_detail)
        toolbar.setOnMenuItemClickListener {
            Toast.makeText(requireContext(), "item: $it", Toast.LENGTH_SHORT).show()
            true
        }

        val appbarLayout = dataBinding!!.appbar
        val collapsingToolbarLayout = dataBinding!!.collapsingToolbarLayout

        var isShow = true
        var scrollRange = -1
        appbarLayout.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { barLayout, verticalOffset ->
            if (scrollRange == -1){
                scrollRange = barLayout?.totalScrollRange!!
            }
            if (scrollRange + verticalOffset == 0){
                collapsingToolbarLayout.title = "Title Collapse"
                isShow = true
            } else if (isShow){
                collapsingToolbarLayout.title = " " //careful there should a space between double quote otherwise it wont work
                isShow = false
            }
        })

    }

    override fun getLayoutRes(): Int = R.layout.fragment_login2
}
