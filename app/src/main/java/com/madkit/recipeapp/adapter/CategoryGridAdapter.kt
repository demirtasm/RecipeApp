package com.madkit.recipeapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.madkit.recipeapp.databinding.ItemCategoryGridBinding

class CategoryGridAdapter(
    private val categoryList: List<String>,
    private val onItemClick: (String) -> Unit
) : RecyclerView.Adapter<CategoryGridAdapter.CategoryGridViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryGridViewHolder {
        val binding = ItemCategoryGridBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryGridViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryGridViewHolder, position: Int) {
        holder.bind(categoryList[position])
    }

    override fun getItemCount(): Int = categoryList.size

    inner class CategoryGridViewHolder(val binding: ItemCategoryGridBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(category: String) {
            binding.textViewCategoryGrid.text = category
            binding.root.setOnClickListener {
                onItemClick(category)
            }
        }
    }
}
