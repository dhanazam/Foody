package com.dhanazam.foody.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dhanazam.foody.models.Result
import com.dhanazam.foody.util.Constants.Companion.FAVORITE_RECIPES_TABLE

@Entity(tableName = FAVORITE_RECIPES_TABLE)
class FavoritesEntity (
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var result: Result
)