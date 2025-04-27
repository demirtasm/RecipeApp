package com.madkit.recipeapp.api

import com.madkit.recipeapp.BuildConfig.API_KEY
import com.madkit.recipeapp.models.CategoriesResponse
import com.madkit.recipeapp.util.Constants.END_POINT
import okhttp3.Response
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET(END_POINT)
    suspend fun getCategories(
        @Query("type") type: String,
        @Query("number") number: Int = 20,
        @Query("apiKey") apiKey: String = API_KEY
    ): CategoriesResponse
}