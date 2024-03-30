package com.dhanazam.foody.models

import com.google.gson.annotations.SerializedName

data class FoodRecipe(
    @SerializedName("results")
    val result: List<Result>
)
