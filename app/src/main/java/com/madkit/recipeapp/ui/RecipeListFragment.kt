package com.madkit.recipeapp.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import com.madkit.recipeapp.R
import com.madkit.recipeapp.databinding.FragmentRecipeListBinding
import com.madkit.recipeapp.viewmodel.RecipeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlin.getValue

@AndroidEntryPoint
class RecipeListFragment : Fragment() {

    private lateinit var binding: FragmentRecipeListBinding
    private lateinit var selectedCategory: String
    val viewModel: RecipeViewModel by viewModels()

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
        val selectedType = selectedCategory
       viewModel.getRecipe(selectedType)

       viewModel.recipeResponse.observe(viewLifecycleOwner) { categoriesResponse ->
          Log.e("TAGXX",""+categoriesResponse.results)
       }
    }
}