# Navigation Components Tutorials
Tutorials for learning Navigation Components starting with simple set up, continues with adding top menus, passing arguments via navigation graphs and combining them with different Material Design widgets such as ```BottomNavigationView```, ```Toolbar```, ```ViewPager2```, ```TabLayout``` and **dynamic feature module** navigation with ```DynamicNavHostFragment```.

## Overview
* Add, navigation graph and navigate to fragments with actions and animations
    * [Tutorial1-Navigation-NavGraph](https://github.com/SmartToolFactory/NavigationComponents-Tutorials/tree/master/Tutorial1-1Navigation-NavGraph)

* Nested navigation graphs, NavHostFragment and navigation
    * [Tutorial1-2Navigation-NestedNavGraph](https://github.com/SmartToolFactory/NavigationComponents-Tutorials/tree/master/Tutorial1-3Navigation-NestedNavHost)


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

Uses seperate and nested fragments with each it's own navigation graph.

```nav_graph``` is the main graph for navigation has navigation to **CameraFragment** or other graphs such as ```nav_graph_dashboard``` or ```nav_graph_home```
Nested graphs are part of the same ```navHostFragment?.childFragmentManager```

### [Tutorial1-3Navigation-NestedNavHost](https://github.com/SmartToolFactory/NavigationComponents-Tutorials/tree/master/Tutorial1-3Navigation-NestedNavHost)

Covers how nested graphs with their own back stack or ```NavHostFragment``` work. You can check out both main and home childFragmentManager back stack entry and fragment count by examining Toast or log messages.

### Note
Main graph back stack is controlled by ```NavHostFragment.getChildFragmentManager```

When a nested navigation graph or ```NavHostFragment``` is added it's back stack is retrieved
using a childFragmentManager.

When a fragment is from a nested navigation graph is on screen when you navigate back or forth it's current position
changes only for the current NavHostFragment, main back stack does not change.

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

### Note
Back navigation does NOT work for the first fragment in back stack of ```HomeNavHostFragment``` for this example, because

 ```
if (navController!!.currentDestination == null || navController!!.currentDestination!!.id == navController!!.graph.startDestination) {
    navController?.navigate(R.id.homeFragment1)
}
 ```

and start destination is ```HomeNavHostFragment```itself , but  last fragment, currentDestination on graph is  ```HomeFragment1``` when back button is pressed while ```HomeFragment2``` is on screen.

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

### [Tutorial6-0NavigationUI-ViewPager](https://github.com/SmartToolFactory/NavigationComponents-Tutorials/tree/master/Tutorial6-0NavigationUI-ViewPager)

**Navigation Architecture**

```
MainActivity (Appbar + Toolbar)
|- MainNavHost
    |
    | FragmentViewPagerContainer(ViewPager2 + TabLayout)
        |- HomeFragment1 -> HomeFragment2 -> HomeFragment3
        |- DashboardFragment1 -> DashboardFragment2 -> DashboardFragment3
```

Covers how to create a ```ViewPager2```with navigation in main back stack, in this example ```ViewPager2```pages do not have their own back stacks. It's covered in tutorial Tutorial6-2.


<p align="center">
    <img src="./screenshots/Tutorial6-0.gif"/>
</p>

### [Tutorial6-1NavigationUI-ViewPager](https://github.com/SmartToolFactory/NavigationComponents-Tutorials/tree/master/Tutorial6-1NavigationUI-ViewPager2)

Same as previous tutorial except only with one difference,  ```data binding``` is used for binding.

### Note
Data binding that is not null(or non-nullable) after ```Fragment.onDestroyView``` when ```ViewPager2```is inside a fragment causing leak canary to show data binding related **MEMORY LEAK** for this fragment when used in ```ViewPager2``. Also you need to set adapter of ViewPager2 either to prevent memory leaks, and another one is due to TabLayouts which is covered in later sections. Check out this [stackoverflow question](https://stackoverflow.com/questions/62851425/viewpager2-inside-a-fragment-leaks-after-replacing-the-fragment-its-in-by-navig) for more details.

### [Tutorial6-2NavigationUI-NestedNavHost](https://github.com/SmartToolFactory/NavigationComponents-Tutorials/tree/master/Tutorial6-2NavigationUI-NestedNavHost)

**Navigation Architecture**

```

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

Covers ```ViewPager2``` and it's pages each with it's own back stack or navigation graphs.

```
NavHostFragment use ```NavController```to navigate back/forth in any page.
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

**Navigation Architecture**

```

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

In this tutorial  MainActivity has it's appbar that navigation is controlled using the ``NavController``` retrieved from `` NavHostFragment``  via `` LiveData``

<p align="center">
    <img src="./screenshots/Tutorial6-3.gif"/>
</p>

### Note

There is an issue with rotation, when device rotated `` ActivityFragmentStateAdapter.createFragment``  method is not called and it's not possible to access ```NavController``` of newly created fragments. If you do not wish to have a rotatable app
you can use live data or ViewModel to get current ```NavController``` to change appbar title and get other
properties of ```NavController```. LiveData is observed in ```MainActivity``` to set appbar title


### [Tutorial6-4NavigationUI-ViewPager2-FragmentToolbar-NestedNavigation](https://github.com/SmartToolFactory/NavigationComponents-Tutorials/tree/master/Tutorial6-4NavigationUI-ViewPager2-FragmentToolbar-NestedNavigation)

 **Navigation Architecture**

 ```
  MainActivity
     |- MainNavHost
        |
        |- ViewPagerContainerFragment(ViewPager)
            |
            |- HomeNavHostFragment(Appbar + Toolbar)
            |  |- HF1 -> HF2 -> HF3
            |
            |- DashboardNavHostFragment(Appbar Toolbar)
            |  |- DF1 -> DF2 -> DF3
            |
            |- NotificationHostFragment(Appbar Toolbar)
               |- NF1 -> NF2 -> NF3

 ```

In this tutorial each ```NavHostFragment``` has it's own toolbar
 They can navigate back with back arrow when navigated to an inner/nested fragment in pages of ViewPager

<p align="center">
    <img src="./screenshots/Tutorial6-4.gif"/>
</p>

 Using ```FragmentStateAdapter.registerFragmentTransactionCallback``` with ```FragmentStateAdapter``` solves back navigation instead of using ```OnBackPressedCallback.handleOnBackPressed``` in every ```NavHostFragment``` as answered [here](https://stackoverflow.com/questions/61779776/leak-canary-detects-memory-leaks-for-tablayout-with-viewpager2)



 ```
 init {
     // Add a FragmentTransactionCallback to handle changing
     // the primary navigation fragment
     registerFragmentTransactionCallback(object : FragmentTransactionCallback() {
         override fun onFragmentMaxLifecyclePreUpdated(
             fragment: Fragment,
             maxLifecycleState: Lifecycle.State
         ) = if (maxLifecycleState == Lifecycle.State.RESUMED) {

             // This fragment is becoming the active Fragment - set it to
             // the primary navigation fragment in the OnPostEventListener
             OnPostEventListener {
                 fragment.parentFragmentManager.commitNow {
                     setPrimaryNavigationFragment(fragment)
                 }
             }

         } else {
             super.onFragmentMaxLifecyclePreUpdated(fragment, maxLifecycleState)
         }
     })
 }
```

If defaultNavHost is not set to true true for any ```NavHostFragments```  by setting  ```app:defaultNavHost="true"`` in xml or programmatically
snippet above will not work.

### [Tutorial6-5NavigationUI-ViewPager2-FragmentToolbar-MixedNavigation](https://github.com/SmartToolFactory/NavigationComponents-Tutorials/tree/master/Tutorial6-5NavigationUI-ViewPager2-FragmentToolbar-MixedNavigation)

 **Navigation Architecture**

 ```
 MainActivity
    |- MainNavHost
       |
       |- ParenNavHost((Appbar + Toolbar)
           |
           |- ViewPagerContainerFragment(ViewPager2)
           |   |
           |   |- HomeNavHostFragment(Appbar + Toolbar)
           |   |  |- HF1 -> HF2 -> HF3
           |   |
           |   |- DashboardNavHostFragment(Appbar + Toolbar)
           |   |  |- DF1 -> DF2 -> DF3
           |   |
           |   |- NotificationHostFragment(Appbar + Toolbar)
           |   |  |- NF1 -> NF2 -> NF3
           |   |
           |   |-LoginFragment1
           |
           |- LoginFragment1 -> LoginFragment2

 ```

In this tutorial each ```NavHostFragment``` has it's own toolbar, also ```ParentNavHostFragment``` has it's own toolbar either.

<p align="center">
    <img src="./screenshots/Tutorial6-5.gif"/>
</p>

LoginFragment2 in this example is added to  back stack of ParentNavHostFragment because of that it does not have any association with toolbar in  ```ViewPagerContainerFragment```

ParentNavHostFragment's role is to have it's own Appbar to contain login fragments and navigate through them using Appbar. Without ```ParentNavHostFragment``` we navigate to ```LoginFragment2``` that has no Appbar.

Visibility of ```ParentNavHostFragment``` is changed via liveData of ```AppbarViewModel```

 However, there is an issue whenever Toolbar that is not belong to fragments appear or disappear.


 ### [Tutorial6-6NavigationUI-ViewPager2-Appbar-MixedNavigation-ViewModel](https://github.com/SmartToolFactory/NavigationComponents-Tutorials/tree/master/Tutorial6-6NavigationUI-ViewPager2-Appbar-MixedNavigation-ViewModel)

  **Navigation Architecture**

  ```
 MainActivity
    |- MainNavHost
       |
       |- ParenNavHost(Appbar + Toolbar) Here because we wish to have toolbar inside Fragment
           |
           |- ViewPagerContainerFragment(TabLayout + ViewPager2)
           |   |
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

In this tutorial, only ```ParentNavHostFragment``` has Appbar and Toolbar. Navigation of individual
NavHostFragments is done via LiveData belong to ```AppbarViewModel.currentNavController``` which
returns ```NavController``` of current ```NavHostFragment``` on screen due. Current ```NavController``` is set ```onResume``` to make sure to
set it only when the current fragment is visible, it's instead of checking if fragment is on screen and visible.

<p align="center">
    <img src="./screenshots/Tutorial6-6.gif"/>
</p>

```ParentNavHostFragment```'s role is to have it's own Appbar to contain login fragments and navigate through them using Appbar. Without ParentNavHostFragment we navigate to ```LoginFragment2``` that has no Appbar if it's inside ```ViewPagerContainerFragment```.

It can be done by putting Appbar to ```MainActivity``` but purpose here is to put
 Appbar + Toolbar inside a fragment to be able to use with ```BottomNavigationView``` for instance



 ### [Tutorial7-1BNV-ViewPager2-NestedNavigation](https://github.com/SmartToolFactory/NavigationComponents-Tutorials/tree/master/Tutorial7-1BNV-ViewPager2-NestedNavigation)

  **Navigation Architecture**

  ```
   MainActivity(BottomNavigationView + + ViewPager2 + Appbar + Toolbar)
         |- HomeNavHostFragment
         |   |- HF1 -> HF2 -> HF3
         |
         |- DashboardNavHostFragment
         |   |- DF1 -> DF2 -> DF3
         |
         |- NotificationHostFragment
         |   |- NF1 -> NF2 -> NF3

  ```

In this example ```BottomNavigationView``` selects which page of [ViewPager2] should be opened using
```BottomNavigationView.setOnNavigationItemSelectedListener```

<p align="center">
    <img src="./screenshots/Tutorial7-1.gif"/>
</p>

Appbar title is changed using  LiveData ```AppbarViewModel.currentNavController``` of visible ```NavHostFragment```  on screen of ViePager2 page



Change current nav controller to set appbar title

```
appbarViewModel.currentNavController.observe(this, Observer { it ->

    it?.let { event: Event<NavController> ->
        event.getContentIfNotHandled()?.let { navController ->
            val appBarConfig = AppBarConfiguration(navController.graph)
            dataBinding.toolbar.setupWithNavController(navController, appBarConfig)
        }
    }
})

```

 ### [Tutorial7-2BNV-ViewPager2-ComplexArchitecture](https://github.com/SmartToolFactory/NavigationComponents-Tutorials/tree/master/Tutorial7-2BNV-ViewPager2-ComplexArchitecture)

  **Navigation Architecture**