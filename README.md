# NavigationComponents-Tutorials
Tutorials for learning Navigation Components with simple set up, top menus, passing arguments via navigation graphs and combining them with different Material Design widgets such as ```BottomNavigationView```, ```Toolbar```, ```ViewPager2```, ```TabLayout``` and **dynamic feature module** navigation with ```DynamicNavHostFragment```.



## Tutorial Coverage

* [Tutorial0-0MaterialDesign](https://github.com/SmartToolFactory/NavigationComponents-Tutorials/tree/master/Tutorial0-0MaterialDesign) Covers basic BottomNavigationView and ViewPager usage without any navigatiom components. This is a little bit like warm up before moving to more complex ones including navigation with ViewPager2

* [Tutorial1-Navigation-NavGraph](https://github.com/SmartToolFactory/NavigationComponents-Tutorials/tree/master/Tutorial1-1Navigation-NavGraph) Covers how to use create navigation graph ```nav_graph.xml``` inside navigation folder, and  ```NavHostFragment```in ```activity_main.xml``` layout file.

### Note
One important note to add is navigation uses FragmentTransaction.replace() to navigate next fragment inside specified ```NavHostFragment```

```
    <androidx.fragment.app.FragmentContainerView
        android:id="@+id/nav_host_fragment"
        android:name="androidx.navigation.fragment.NavHostFragment"
        android:layout_width="0dp"
        android:layout_height="0dp"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintRight_toRightOf="parent"
        app:layout_constraintTop_toTopOf="parent"

        app:defaultNavHost="true"
        app:navGraph="@navigation/nav_graph" />
```

There are multiple ways to navigate from one fragment to another using a ```NavController```, for instance

```

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

```

Check out this tutorial if you wish to get familiar with basic consepts, animation and navigating with ```popUpTo```and ```popUpToInclusive```

* [Tutorial1-2Navigation-NestedNavGraph](https://github.com/SmartToolFactory/NavigationComponents-Tutorials/tree/master/Tutorial1-3Navigation-NestedNavHost) Uses seperate and nested fragments with each it's own nav graph.

```nav_graph``` is the main graph for navigation which can directly go to **CameraFragment** or ```nav_graph_dashboard``` or ```nav_graph_home```
Nested graphs are part of the same ```navHostFragment?.childFragmentManager```

* [Tutorial1-3Navigation-NestedNavHost](https://github.com/SmartToolFactory/NavigationComponents-Tutorials/tree/master/Tutorial1-3Navigation-NestedNavHost)

Covers how nested graphs with their own back stack or ```NavHostFragment``` work. You can check out both main and home childFragmentManager back stack entry and fragment counts by examining Toast or log messages.

### Note
 Main graph back stack is controlled by ```NavHostFragment.getChildFragmentManager```

 When a nested navigation graph or ```NavHostFragment``` added also it's back stack is retrieved
 using childFragmentManager.

 When on nested graph back button navigates from that back stack to current entry position on main graph.

```HomeNavHostFragment``` should be created, in this tutorial we create it by setting it by
 initial destination


 ```
 val callback = object : OnBackPressedCallback(false) {

    override fun handleOnBackPressed() {

        // Get NavHostFragment
        val navHostFragment =
            childFragmentManager.findFragmentById(nestedNavHostFragmentId)
        // ChildFragmentManager of the current NavHostFragment
        val navHostChildFragmentManager = navHostFragment?.childFragmentManager

        // Check if it's the root of nested fragments in this navhosts
        if (navController?.currentDestination?.id == navController?.graph?.startDestination) {

            /*
                Disable this callback because calls OnBackPressedDispatcher
                 gets invoked  calls this callback  gets stuck in a loop
             */
            isEnabled = false
            requireActivity().onBackPressed()
            isEnabled = true

        } else if (isVisible) {
            navController?.navigateUp()
        }

    }
}

 ```

  #### Note
 Back navigation does NOT work for the first fragment in back stack of ```HomeNavHostFragment``` for this example, because

 ```
if (navController!!.currentDestination == null || navController!!.currentDestination!!.id == navController!!.graph.startDestination) {
    navController?.navigate(R.id.homeFragment1)
}
 ```

 and start destination is ```HomeNavHostFragment```itself while it's navigation to ```HomeFragment1```on last back press to move back from current sub back stack

 Change ```app:startDestination="@id/home_dest"``` to ```app:startDestination="@id/homeFragment1"```  to solve back press issue for ```HomeNavHostFragment```, it's just set to demonstrate how start destination change back press.

  ```
 <navigation xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:app="http://schemas.android.com/apk/res-auto"
        xmlns:tools="http://schemas.android.com/tools"
        android:id="@+id/nav_graph_home"
        app:startDestination="@id/home_dest">

    <fragment
            android:id="@+id/home_dest"
            android:name="com.smarttoolfactory.tutorial1_3navigation_nestednavhost.navhost.HomeNavHostFragment"
            android:label="HomeHost"
            tools:layout="@layout/fragment_navhost_home" />

    <fragment
            android:id="@+id/homeFragment1"
            android:name="com.smarttoolfactory.tutorial1_3navigation_nestednavhost.blankfragment.HomeFragment1"
            android:label="HomeFragment1"
            tools:layout="@layout/fragment_home1">

    </fragment>

</navigation>
 ```

 * [Tutorial6-0NavigationUI-ViewPager](https://github.com/SmartToolFactory/NavigationComponents-Tutorials/tree/master/Tutorial1-3Navigation-NestedNavHost) Covers how to create a ```ViewPager2```with navigation in main back stack, in this example ```ViewPager2```pages do not have their own back stack. It's covered in tutorials Tutorial6-2.

**Navigation Architecture**

```
MainActivity (Appbar + Toolbar)
|- MainNavHost
    |
    | FragmentViewPagerContainer(ViewPager2 + TabLayout)
        |- HomeFragment1 -> HomeFragment2 -> HomeFragment3
        |- DashboardFragment1 -> DashboardFragment2 -> DashboardFragment3
```



