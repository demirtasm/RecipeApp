package com.madkit.recipeapp.api

import com.madkit.recipeapp.BuildConfig.API_KEY
import com.madkit.recipeapp.models.CategoriesResponse
import com.madkit.recipeapp.models.RecipeDetailResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("recipes/complexSearch")
    suspend fun getCategories(
        @Query("type") type: String,
        @Query("number") number: Int = 20,
        @Query("apiKey") apiKey: String = API_KEY
    ): CategoriesResponse

    @GET("recipes/{id}/information")
    suspend fun getRecipeDetail(
        @Path("id") id: Int?,
        @Query("apiKey") apiKey: String = API_KEY
    ): RecipeDetailResponse
}