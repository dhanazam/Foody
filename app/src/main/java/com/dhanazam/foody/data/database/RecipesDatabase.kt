package com.dhanazam.foody.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.dhanazam.foody.data.database.entities.FavoritesEntity
import com.dhanazam.foody.data.database.entities.RecipesEntity

@Database(
    entities = [RecipesEntity::class, FavoritesEntity::class],
    version = 2,
    exportSchema = false
)
@TypeConverters(RecipesTypeConverter::class)
abstract class RecipesDatabase: RoomDatabase() {
    abstract fun recipesDao(): RecipesDao
}