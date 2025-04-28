package com.madkit.recipeapp.repository

import com.madkit.recipeapp.api.ApiService
import javax.inject.Inject

class RecipeRepository
@Inject constructor(private val apiService : ApiService){
    suspend fun getRecipeCategories(type: String) = apiService.getCategories(type)

    suspend fun getRecipeDetail(id: Int?) = apiService.getRecipeDetail(id)
}