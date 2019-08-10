package com.smarttoolfactory.tutorial1_3_navigation_navigationui


import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.smarttoolfactory.tutorial1_3_navigation_navigationui.adapter.BaseAdapter
import com.smarttoolfactory.tutorial1_3_navigation_navigationui.adapter.MyAdapter
import com.smarttoolfactory.tutorial1_3_navigation_navigationui.databinding.ActivityMainBinding
import com.smarttoolfactory.tutorial1_3_navigation_navigationui.model.ActivityClassModel
import java.util.*

class MainActivity : AppCompatActivity(), BaseAdapter.OnRecyclerViewItemClickListener {


    private val activityClassModels = ArrayList<ActivityClassModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        title = "MainActivity"

        val activityMainBinding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)

        // Add Activities to list on RecyclerView
        activityClassModels.add(ActivityClassModel(MenuNavigationActivity::class.java))


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
        val adapter = MyAdapter(activityClassModels)
        recyclerView.adapter = adapter
        adapter.setOnItemClickListener(this)


    }

    @Override
    override fun onItemClicked(view: View, position: Int) {

        val intent = Intent(this@MainActivity, activityClassModels[position].clazz)
        startActivity(intent)

    }
}
