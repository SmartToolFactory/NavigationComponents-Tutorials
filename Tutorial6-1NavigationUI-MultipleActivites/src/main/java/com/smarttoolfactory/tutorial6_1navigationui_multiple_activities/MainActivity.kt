package com.smarttoolfactory.tutorial6_1navigationui_multiple_activities


import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.smarttoolfactory.tutorial6_1navigationui_multiple_activities.adapter.BaseAdapter
import com.smarttoolfactory.tutorial6_1navigationui_multiple_activities.adapter.MyAdapter
import com.smarttoolfactory.tutorial6_1navigationui_multiple_activities.databinding.ActivityMainBinding
import com.smarttoolfactory.tutorial6_1navigationui_multiple_activities.model.ActivityClassModel
import com.smarttoolfactory.tutorial6_1navigationui_multiple_activities.navmenu.MenuNavigationActivity
import com.smarttoolfactory.tutorial6_1navigationui_multiple_activities.navnavigationview.NavigationViewNavigationActivity
import java.util.*

class MainActivity : AppCompatActivity(), BaseAdapter.OnRecyclerViewItemClickListener {


    private val activityClassModels = ArrayList<ActivityClassModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        title = "MainActivity"

        val activityMainBinding = DataBindingUtil.setContentView<ActivityMainBinding>(this,
            R.layout.activity_main
        )

        // Add Activities to list on RecyclerView
        activityClassModels.add(
            ActivityClassModel(
                MenuNavigationActivity::class.java
            )
        )
        activityClassModels.add(
            ActivityClassModel(
                NavigationViewNavigationActivity::class.java
            )
        )

        val recyclerView = activityMainBinding.recyclerView

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true)

        // use a linear layout manager
        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager


        recyclerView.addItemDecoration(
            DividerItemDecoration(
                this,
                DividerItemDecoration.VERTICAL
            )
        )

        // define an adapter
        val adapter =
            MyAdapter(activityClassModels)
        recyclerView.adapter = adapter
        adapter.setOnItemClickListener(this)


    }

    @Override
    override fun onItemClicked(view: View, position: Int) {

        val intent = Intent(this@MainActivity, activityClassModels[position].clazz)
        startActivity(intent)

    }
}
