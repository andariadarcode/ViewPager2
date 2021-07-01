package com.andariadar.viewpager2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.andariadar.util.CubeOutScalingTransformer
import com.andariadar.viewpager2.adapter.ViewPagerAdapter
import com.andariadar.viewpager2.databinding.ActivityMainBinding
import com.andariadar.viewpager2.model.Page

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var pageList: MutableList<Page>
    private lateinit var adapter: ViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        initPageList()
        initAdapter()

        binding.apply {
            btnAdd.setOnClickListener {
                // create new list from previous one
                val newList: MutableList<Page> = pageList.toMutableList()
                newList.add(Page(4, "Page 5", "text 5", R.color.blue))
                adapter.submitList(newList)
            }
        }
    }

    private fun initAdapter() {
        adapter = ViewPagerAdapter()
        binding.apply {
            viewPager.adapter = adapter
            viewPager.setPageTransformer(CubeOutScalingTransformer())
        }

        // pass list to adapter for the first time
        adapter.submitList(pageList)
    }

    private fun initPageList() {
        pageList = mutableListOf(
            Page(0,"Page 1", "text 1", R.color.yellow),
            Page(1,"Page 2", "text 2", R.color.red),
            Page(2,"Page 3", "text 3", R.color.green),
            Page(3,"Page 4", "text 4", R.color.purple)
        )
    }
}

