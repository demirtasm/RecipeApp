package com.madkit.recipeapp.di

import com.madkit.recipeapp.api.ApiService
import com.madkit.recipeapp.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RecipeModule {

    @Provides
    @Singleton
    fun provideRetrofitInstance(): ApiService = Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(
        GsonConverterFactory.create()).build().create(ApiService::class.java)
}