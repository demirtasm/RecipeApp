package com.madkit.recipeapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.madkit.recipeapp.models.CategoriesResponse
import com.madkit.recipeapp.models.RecipeDetailResponse
import com.madkit.recipeapp.repository.RecipeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeViewModel @Inject constructor(private val repository: RecipeRepository) : ViewModel() {

    private val _recipeResponse = MutableLiveData<CategoriesResponse>()
    val recipeResponse: LiveData<CategoriesResponse> = _recipeResponse

    private val _recipeDetailResponse = MutableLiveData<RecipeDetailResponse>()
    val recipeDetailResponse: LiveData<RecipeDetailResponse> = _recipeDetailResponse


     fun getRecipe(type: String) = viewModelScope.launch {
        try {
            val response = repository.getRecipeCategories(type)
            _recipeResponse.value = response
        } catch (e: Exception){
            e.printStackTrace()
        }

    }

    fun getRecipeDetail(id: Int?) = viewModelScope.launch {
        try {
            val response = repository.getRecipeDetail(id)
            _recipeDetailResponse.value = response
        }catch (e: Exception){
            e.printStackTrace()
        }
    }
}