package com.madkit.recipeapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.madkit.recipeapp.R
import com.madkit.recipeapp.adapter.CategoryGridAdapter
import com.madkit.recipeapp.databinding.FragmentCategoryGridBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoryGridFragment : Fragment() {

    private lateinit var binding: FragmentCategoryGridBinding
    private val categories = listOf(
        "main course",
        "side dish",
        "dessert",
        "appetizer",
        "salad",
        "bread",
        "breakfast",
        "soup",
        "beverage",
        "sauce",
        "drink"
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCategoryGridBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = CategoryGridAdapter(categories) { category ->
            navigateToCategory(category)
        }
        binding.recyclerViewCategoryGrid.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.recyclerViewCategoryGrid.adapter = adapter
    }
    private fun navigateToCategory(category: String) {
        val action =
            CategoryGridFragmentDirections.actionCategoryGridFragmentToRecipeListFragment(category)
        findNavController().navigate(action)
    }
}

