package com.andariadar.viewpager2.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.andariadar.viewpager2.databinding.ItemViewPagerBinding
import com.andariadar.viewpager2.model.Page

class ViewPagerAdapter: RecyclerView.Adapter<ViewPagerAdapter.ViewPagerViewHolder>() {
    inner class ViewPagerViewHolder(private val binding: ItemViewPagerBinding):
        RecyclerView.ViewHolder(binding.root) {
            fun bind(contact: Page) {
                binding.apply {
                    numeral.text = contact.num
                    text.text = contact.text
                    layout.setBackgroundResource(contact.color)
                }
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerViewHolder {
        val binding = ItemViewPagerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewPagerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewPagerViewHolder, position: Int) {
        val contact = differ.currentList[position]
        holder.bind(contact)
    }

    override fun getItemCount() = differ.currentList.size

    private val differCallback = object : DiffUtil.ItemCallback<Page>() {
        override fun areItemsTheSame(oldItem: Page, newItem: Page): Boolean {
            return oldItem.text == newItem.text
        }

        override fun areContentsTheSame(oldItem: Page, newItem: Page): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    fun submitList(data: MutableList<Page>) {
        differ.submitList(data)
    }
}