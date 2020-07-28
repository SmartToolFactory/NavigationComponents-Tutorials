# NavigationComponents-Tutorials
Tutorials for learning Navigation Components with simple set up, top menus, passing arguments via navigation graphs and combining them with different Material Design widgets such as ```BottomNavigationView```, ```Toolbar```, ```ViewPager2```, ```TabLayout``` and **dynamic feature module** navigation with ```DynamicNavHostFragment```.



## Tutorial Coverage

### [Tutorial0-0MaterialDesign](https://github.com/SmartToolFactory/NavigationComponents-Tutorials/tree/master/Tutorial0-0MaterialDesign)
Covers basic BottomNavigationView and ViewPager usage without any navigatiom components. This is a little bit like warm up before moving to more complex ones including navigation with ViewPager2

### [Tutorial1-Navigation-NavGraph](https://github.com/SmartToolFactory/NavigationComponents-Tutorials/tree/master/Tutorial1-1Navigation-NavGraph)
Covers how to use create navigation graph ```nav_graph.xml``` inside navigation folder, and  ```NavHostFragment```in ```activity_main.xml``` layout file.

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

### [Tutorial1-2Navigation-NestedNavGraph](https://github.com/SmartToolFactory/NavigationComponents-Tutorials/tree/master/Tutorial1-3Navigation-NestedNavHost)

Uses seperate and nested fragments with each it's own nav graph.

```nav_graph``` is the main graph for navigation which can directly go to **CameraFragment** or ```nav_graph_dashboard``` or ```nav_graph_home```
Nested graphs are part of the same ```navHostFragment?.childFragmentManager```

[Tutorial1-3Navigation-NestedNavHost](https://github.com/SmartToolFactory/NavigationComponents-Tutorials/tree/master/Tutorial1-3Navigation-NestedNavHost)

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

### [Tutorial6-0NavigationUI-ViewPager](https://github.com/SmartToolFactory/NavigationComponents-Tutorials/tree/master/Tutorial1-3Navigation-NestedNavHost)

Covers how to create a ```ViewPager2```with navigation in main back stack, in this example ```ViewPager2```pages do not have their own back stack. It's covered in tutorials Tutorial6-2.

**Navigation Architecture**

```
MainActivity (Appbar + Toolbar)
|- MainNavHost
    |
    | FragmentViewPagerContainer(ViewPager2 + TabLayout)
        |- HomeFragment1 -> HomeFragment2 -> HomeFragment3
        |- DashboardFragment1 -> DashboardFragment2 -> DashboardFragment3
```

<p align="center">
  <img src="./screenshots/Tutorial6-0.gif"/>
</p>

### [Tutorial6-1NavigationUI-ViewPager](https://github.com/SmartToolFactory/NavigationComponents-Tutorials/tree/master/Tutorial6-1NavigationUI-ViewPager2)

Same as previous tutorial with only one difference  ```data binding``` is used in layouts.

### Note
Data binding that is not null(or non-nullable) after ```Fragment.onDestroyView``` when ```ViewPager2```is inside a fragment causing leak canary to show data binding related **MEMORY LEAK** for this fragment when used in ```ViewPager2``. Also you need to set adapter of ViewPager2 either to prevent memory leaks, and another one is due to TabLayouts which is covered in later sections. Check out this [stackoverflow question](https://stackoverflow.com/questions/62851425/viewpager2-inside-a-fragment-leaks-after-replacing-the-fragment-its-in-by-navig) for more details.

### [Tutorial6-2NavigationUI-NestedNavHost](https://github.com/SmartToolFactory/NavigationComponents-Tutorials/tree/master/Tutorial6-2NavigationUI-NestedNavHost)

**Navigation Architecture**

Covers ```ViewPager2``` each with it's own back stack using fragments that have their own nested

```
NavHostFragments to have their own back stack, back/forth navigation.
```

```
**Navigation Architecture**

 MainActivity (Appbar + Toolbar)
    |- MainNavHost
       |
       |- ViewPagerContainerFragment(ViewPager2 + TabLayout)
       |   |- HomeNavHostFragment
       |   |  |- HF1 -> HF2 -> HF3
       |   |
       |   |- DashboardNavHostFragment
       |   |  |- DF1 -> DF2 -> DF3
       |   |
       |   |- NotificationHostFragment
       |   |  |- NF1 -> NF2 -> NF3
       |   |
       |   |-LoginFragment1
       |
       |- LoginFragment1 -> LoginFragment2
```

<p align="center">
  <img src="./screenshots/Tutorial6-2.gif"/>
</p>

### Note
This tutorial has very important aspects for ```ViewPager2``` navigation

1. Creating ```NavHostFragment``` for each page and can navigate inside them, each page has
    it's own nav graph.

    in each layout file ```NavHostFragment``` inside is retrieved using
    ``
            val nestedNavHostFragment =
                childFragmentManager.findFragmentById(nestedNavHostFragmentId) as? NavHostFragment
            navController = nestedNavHostFragment?.navController`
    ``

    ```HomeNavHostFragment``` uses the first fragment that is displayed on screen **HomeFragment1 while   ```DashboardNavHostFragment``` uses graph with itself as start destination so it should check for the ```NavController.getCurrentDestination()``` to navigate to it when device rotated

    ```LoginFragment1``` is added to main graph, because of that appbar back navigation only works with the ```ViewPagerContainerFragment```'s ```NavController```

2. How to use back navigation with ```OnBackPressedCallback```, there is an alternative and more simple way
    to handle back navigation for ```ViewPager2``` but this also a way to keep in mind if more customization is required.
    If you do not handle back navigation Activity's back press gets called and application starts from onCreate.

3. Checking out memory leaks with data binding, ViewPager2 adapter and lifecylce.
     * You should set **data binding** to **null** or you will get memory leaks for this ViewPager2 which is itself also inside a fragment

     * You should set ViePager2's adapter to null in ```onDestroyView```

     * 🔥 You should use ```ChildFragmentStateAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle)```, not
     the one that takes ```Fragment``` as parameter.
     And use **view's lifecycle** instead of setting Fragment's lifecycle.

     ```
     viewPager.adapter =
                        ChildFragmentStateAdapter(childFragmentManager, viewLifecycleOwner.lifecycle)
     ```
### [Tutorial6-3NavigationUI-ViewPager2-Appbar-NestedNavigation-LiveData](https://github.com/SmartToolFactory/NavigationComponents-Tutorials/tree/master/Tutorial6-3NavigationUI-ViewPager2-Appbar-NestedNavigation-LiveData)

In this tutorial  MainActivity has it's appbar that navigation is controlled using the [NavController] retrieved from [NavHostFragment] via [LiveData]

### Note

Issue with rotation, when device rotated [ActivityFragmentStateAdapter.createFragment] method is not called and it's not possible to access [NavController] of newly created fragments. If you do not wish to have a rotatable app
you can use live data or ViewModel to get current ```NavController``` to change appbar title and get other
properties of ```NavController```. LiveData is observed in ```MainActivity``` to set appbar title


```
**Navigation Architecture**

 MainActivity(Appbar + Toolbar + TabLayout + ViewPager2)
   |
   |- HomeNavHostFragment
   |  |- HF1 -> HF2 -> HF3
   |
   |- DashboardNavHostFragment
   |  |- DF1 -> DF2 -> DF3
   |
   |- NotificationHostFragment
      |- NF1 -> NF2 -> NF3
```

