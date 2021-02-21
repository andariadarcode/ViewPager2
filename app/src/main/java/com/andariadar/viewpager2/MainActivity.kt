package com.andariadar.viewpager2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.andariadar.util.CubeOutScalingTransformer
import com.andariadar.viewpager2.adapter.ViewPagerAdapter
import com.andariadar.viewpager2.databinding.ActivityMainBinding
import com.andariadar.viewpager2.model.Page

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var contactList: List<Page>
    private lateinit var adapter: ViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        initContactList()
        initAdapter()

        binding.apply {
            btnOk.setOnClickListener {
                // creating new list from previous one
                val list: MutableList<Page> = contactList.toMutableList()
                list.add(Page(5, "6", "text 6", R.color.white))
                adapter.submitList(list)
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
        adapter.differ.submitList(contactList)
    }

    private fun initContactList() {
        contactList = listOf(
            Page(0,"1", "text 1", R.color.yellow),
            Page(1,"2", "text 2", R.color.red),
            Page(2,"3", "text 3", R.color.green),
            Page(3,"4", "text 4", R.color.purple),
            Page(4,"5", "text 5", R.color.blue)
        )
    }
}
