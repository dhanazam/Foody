package com.dhanazam.foody.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dhanazam.foody.models.FoodRecipe
import com.dhanazam.foody.util.Constants.Companion.RECIPES_TABLE

@Entity(tableName = RECIPES_TABLE)
class RecipesEntity(
    val foodRecipe: FoodRecipe
) {
    @PrimaryKey(autoGenerate = false)
    var id: Int = 0
}