package com.madkit.recipeapp.models

import com.google.gson.annotations.SerializedName

data class CategoriesResponse(
    @SerializedName("number")
    val number: Int,
    @SerializedName("offset")
    val offset: Int,
    @SerializedName("results")
    val results: List<Result>,
    @SerializedName("totalResults")
    val totalResults: Int
) {
    data class Result(
        @SerializedName("id")
        val id: Int,
        @SerializedName("image")
        val image: String,
        @SerializedName("imageType")
        val imageType: String,
        @SerializedName("title")
        val title: String
    )
}