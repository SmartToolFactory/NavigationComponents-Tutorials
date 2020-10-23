package com.smarttoolfactory.tutorial0_materialdesign

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.smarttoolfactory.tutorial0_materialdesign.chapter1_bottom_navigation_view.Activity1BottomNavigationView
import com.smarttoolfactory.tutorial0_materialdesign.chapter2_1viewpager2_fragments.Activity2_1ViewPagerFragmentAdapter
import com.smarttoolfactory.tutorial0_materialdesign.chapter2_1viewpager2_fragments.Activity2_2ViewPagerMutableFragments
import com.smarttoolfactory.tutorial0_materialdesign.chapter2_1viewpager2_fragments.Activity2_3ViewPagerInsideFragment
import com.smarttoolfactory.tutorial0_materialdesign.chapter_adapter.BaseAdapter
import com.smarttoolfactory.tutorial0_materialdesign.chapter_adapter.ChapterSelectionAdapter
import com.smarttoolfactory.tutorial0_materialdesign.databinding.ActivityMainBinding
import com.smarttoolfactory.tutorial0_materialdesign.model.ActivityClassModel
import java.util.*

class MainActivity : AppCompatActivity(), BaseAdapter.OnRecyclerViewItemClickListener {

    private val activityClassModels = ArrayList<ActivityClassModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        title = "MainActivity"

        val activityMainBinding =
            DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)

        // Add Activities to list to be displayed on RecyclerView
        activityClassModels.add(
            ActivityClassModel(
                Activity1BottomNavigationView::class.java,
                "Activity BottomNavigationView with listener"
            )
        )
        activityClassModels.add(
            ActivityClassModel(
                Activity2_1ViewPagerFragmentAdapter::class.java,
                "Activity ViewPager2 with Fragments and different Adapters"
            )
        )

        activityClassModels.add(
            ActivityClassModel(
                Activity2_2ViewPagerMutableFragments::class.java,
                "Activity ViewPager2 with Mutable Fragments"
            )
        )

        activityClassModels.add(
            ActivityClassModel(
                Activity2_3ViewPagerInsideFragment::class.java,
                "Activity ViewPager2 inside a Fragment"
            )
        )


        val recyclerView = activityMainBinding.recyclerView

        recyclerView.apply {

            // Use fixed size for performance
            setHasFixedSize(true)

            // use a linear layout manager
            layoutManager = LinearLayoutManager(this@MainActivity)

            // Add vertical divider
            addItemDecoration(
                DividerItemDecoration(
                    this@MainActivity,
                    DividerItemDecoration.VERTICAL
                )
            )

            // Add Adapter
            recyclerView.adapter = ChapterSelectionAdapter(activityClassModels).apply {
                setOnItemClickListener(this@MainActivity)
            }
        }
    }

    @Override
    override fun onItemClicked(view: View, position: Int) {
        Intent(this@MainActivity, activityClassModels[position].clazz).also {
            startActivity(it)
        }
    }
}
