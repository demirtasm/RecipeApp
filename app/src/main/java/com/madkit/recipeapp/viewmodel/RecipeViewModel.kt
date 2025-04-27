package com.madkit.recipeapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.madkit.recipeapp.models.CategoriesResponse
import com.madkit.recipeapp.repository.RecipeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeViewModel @Inject constructor(private val repository: RecipeRepository) : ViewModel() {

    private val _recipeResponse = MutableLiveData<CategoriesResponse>()
    val recipeResponse: LiveData<CategoriesResponse> = _recipeResponse


     fun getRecipe(type: String) = viewModelScope.launch {
        try {
            val response = repository.getRecipeCategories(type)
            _recipeResponse.value = response
        } catch (e: Exception){
            e.printStackTrace()
        }

    }
}