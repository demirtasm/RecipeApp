package com.madkit.recipeapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.madkit.recipeapp.databinding.ItemRecipeListBinding
import com.madkit.recipeapp.models.CategoriesResponse
import com.squareup.picasso.Picasso
import androidx.recyclerview.widget.ListAdapter

class RecipeListAdapter() : ListAdapter<CategoriesResponse.Result, RecipeListAdapter.RecipeListViewHolder>(DiffCallback) {

    private var onItemClickListener: ((CategoriesResponse.Result) -> Unit)? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeListViewHolder {
        val binding = ItemRecipeListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecipeListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecipeListViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    inner class RecipeListViewHolder(val binding: ItemRecipeListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: CategoriesResponse.Result) {
            binding.apply {
                tvRecipeTitle.text = item.title
                Picasso.get().load(item.image).into(imgRecipe)
                root.setOnClickListener {
                    onItemClickListener?.invoke(item)
                }
            }
        }
    }

    fun setOnItemClickListener(listener: (CategoriesResponse.Result) -> Unit) {
        onItemClickListener = listener
    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<CategoriesResponse.Result>() {
            override fun areItemsTheSame(oldItem: CategoriesResponse.Result, newItem: CategoriesResponse.Result): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: CategoriesResponse.Result, newItem: CategoriesResponse.Result): Boolean {
                return oldItem == newItem
            }
        }
    }
}