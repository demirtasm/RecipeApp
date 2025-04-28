package com.madkit.recipeapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
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
        (requireActivity() as AppCompatActivity).supportActionBar?.title = selectedCategory
            viewModel.getRecipe(selectedCategory)
        viewModel.recipeResponse.observe(viewLifecycleOwner) { categoriesResponse ->
            recipeAdapter.submitList(categoriesResponse.results)
        }
        recipeAdapter.setOnItemClickListener{
            val direction =
                RecipeListFragmentDirections.actionRecipeListFragmentToRecipeDetailFragment(
                    it.id
                )
            findNavController().navigate(direction)
        }

    }
}