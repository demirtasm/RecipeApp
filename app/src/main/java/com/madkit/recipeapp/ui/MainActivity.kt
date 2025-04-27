package com.madkit.recipeapp.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.madkit.recipeapp.R
import com.madkit.recipeapp.viewmodel.RecipeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    val viewModel: RecipeViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val selectedType = "dessert"
        viewModel.getRecipe(selectedType)

        viewModel.recipeResponse.observe(this) { categoriesResponse ->
           Log.e("TAGXX",""+categoriesResponse.results)
        }
    }
}