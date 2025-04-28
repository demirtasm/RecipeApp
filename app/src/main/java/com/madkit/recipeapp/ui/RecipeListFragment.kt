package com.madkit.recipeapp.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.madkit.recipeapp.R
import com.madkit.recipeapp.adapter.RecipeListAdapter
import com.madkit.recipeapp.databinding.FragmentRecipeListBinding
import com.madkit.recipeapp.viewmodel.RecipeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlin.getValue

@AndroidEntryPoint
class RecipeListFragment : Fragment() {

    private lateinit var binding: FragmentRecipeListBinding
    private lateinit var selectedCategory: String
    val viewModel: RecipeViewModel by viewModels()
    private lateinit var recipeAdapter: RecipeListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRecipeListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args = RecipeListFragmentArgs.fromBundle(requireArguments())
        selectedCategory = args.category
        recipeAdapter = RecipeListAdapter()

        binding.recyclerViewRecipeList.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.recyclerViewRecipeList.adapter = recipeAdapter

        viewModel.getRecipe(selectedCategory)
        viewModel.recipeResponse.observe(viewLifecycleOwner) { categoriesResponse ->
            recipeAdapter.submitList(categoriesResponse.results)
        }

    }
}