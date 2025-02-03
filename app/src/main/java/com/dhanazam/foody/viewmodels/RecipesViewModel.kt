package com.dhanazam.foody.viewmodels

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.dhanazam.foody.data.DataStoreRepository
import com.dhanazam.foody.util.Constants.Companion.API_KEY
import com.dhanazam.foody.util.Constants.Companion.DEFAULT_DIET_TYPE
import com.dhanazam.foody.util.Constants.Companion.DEFAULT_MEAL_TYPE
import com.dhanazam.foody.util.Constants.Companion.DEFAULT_RECIPES_NUMBER
import com.dhanazam.foody.util.Constants.Companion.QUERY_ADD_RECIPE_INFORMATION
import com.dhanazam.foody.util.Constants.Companion.QUERY_API_KEY
import com.dhanazam.foody.util.Constants.Companion.QUERY_DIET
import com.dhanazam.foody.util.Constants.Companion.QUERY_FILL_INGREDIENTS
import com.dhanazam.foody.util.Constants.Companion.QUERY_NUMBER
import com.dhanazam.foody.util.Constants.Companion.QUERY_TYPE
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipesViewModel @Inject constructor (
    application: Application,
    private val dataRepository: DataStoreRepository
    ): AndroidViewModel(application) {

    private var mealType = DEFAULT_MEAL_TYPE
    private var dietType = DEFAULT_DIET_TYPE

    var networkStatus = false

    val readMealAndDietType = dataRepository.readMealAndDietType

    fun saveMealAndDietType(mealType: String, mealTypeId: Int, dietType: String, dietTypeId: Int) =
        viewModelScope.launch(Dispatchers.IO) {
            dataRepository.saveMealAndDietType(mealType, mealTypeId, dietType, dietTypeId)
        }

    fun applyQueries(): HashMap<String, String> {
        val queries: HashMap<String, String> = HashMap()

        viewModelScope.launch {
            readMealAndDietType.collect { value ->
                mealType = value.selectedMealType
                dietType = value.selectedDietType
            }
        }

        queries[QUERY_NUMBER] = DEFAULT_RECIPES_NUMBER
        queries[QUERY_API_KEY] = API_KEY
        queries[QUERY_TYPE] = mealType
        queries[QUERY_DIET] = dietType
        queries[QUERY_ADD_RECIPE_INFORMATION] = "true"
        queries[QUERY_FILL_INGREDIENTS] = "true"

        return queries
    }

    fun showNetworkStatus() {
        if(!networkStatus) {
            Toast.makeText(getApplication(), "No Internet Connection", Toast.LENGTH_SHORT).show()
        }
    }
}