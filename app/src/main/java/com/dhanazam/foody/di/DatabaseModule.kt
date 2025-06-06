package com.dhanazam.foody.di

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import com.dhanazam.foody.data.database.RecipesDatabase
import com.dhanazam.foody.util.Constants.Companion.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        RecipesDatabase::class.java,
        DATABASE_NAME
    ).fallbackToDestructiveMigration().build()


    @Singleton
    @Provides
    fun provideDao(database: RecipesDatabase) = database.recipesDao()
}