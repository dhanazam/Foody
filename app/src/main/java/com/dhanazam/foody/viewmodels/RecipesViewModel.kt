package com.dhanazam.foody.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.dhanazam.foody.util.Constants.Companion.API_KEY
import com.dhanazam.foody.util.Constants.Companion.QUERY_ADD_RECIPE_INFORMATION
import com.dhanazam.foody.util.Constants.Companion.QUERY_API_KEY
import com.dhanazam.foody.util.Constants.Companion.QUERY_DIET
import com.dhanazam.foody.util.Constants.Companion.QUERY_FILL_INGREDIENTS
import com.dhanazam.foody.util.Constants.Companion.QUERY_NUMBER
import com.dhanazam.foody.util.Constants.Companion.QUERY_TYPE

class RecipesViewModel(application: Application): AndroidViewModel(application) {

    fun applyQueries(): HashMap<String, String> {
        val queries: HashMap<String, String> = HashMap()

        queries[QUERY_NUMBER] = "10"
        queries[QUERY_API_KEY] = API_KEY
        queries[QUERY_TYPE] = "snack"
        queries[QUERY_DIET] = "vegan"
        queries[QUERY_ADD_RECIPE_INFORMATION] = "true"
        queries[QUERY_FILL_INGREDIENTS] = "true"

        return queries
    }
}