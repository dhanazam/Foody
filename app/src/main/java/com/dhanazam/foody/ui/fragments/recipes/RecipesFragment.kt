package com.dhanazam.foody.ui.fragments.recipes

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dhanazam.foody.MainViewModel
import com.dhanazam.foody.R
import com.dhanazam.foody.adapter.RecipesAdapter
import com.dhanazam.foody.util.Constants.Companion.API_KEY
import com.dhanazam.foody.util.NetworkResult
import com.facebook.shimmer.ShimmerFrameLayout
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecipesFragment : Fragment() {

    private val mainViewModel: MainViewModel by viewModels()
    private val mAdapter by lazy { RecipesAdapter() }
    private lateinit var mView: View
    private lateinit var recyclerView: RecyclerView
    private lateinit var shimmer: ShimmerFrameLayout
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mView = inflater.inflate(R.layout.fragment_recipes, container, false)
        requestApiData()

        return mView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = mView.findViewById(R.id.recyclerview)
        shimmer = mView.findViewById(R.id.shimmerFrameLayout)
        setupRecyclerView()
    }

    private fun requestApiData() {
        mainViewModel.getRecipes(applyQueries())
        mainViewModel.recipesResponse.observe(viewLifecycleOwner) { response ->
            Log.d("response api -->", response.data.toString())
            when (response) {
                is NetworkResult.Success -> {
                    hideShimmerEffect()
                    response.data?.let { mAdapter.setData(it) }
                }

                is NetworkResult.Error -> {
                    hideShimmerEffect()
                    Toast.makeText(
                        requireContext(),
                        response.message.toString(),
                        Toast.LENGTH_SHORT
                    ).show()
                }

                is NetworkResult.Loading -> {
                    showShimmerEffect()
                }
            }
        }
    }

    private fun applyQueries(): HashMap<String, String> {
        val queries: HashMap<String, String> = HashMap()

        queries["number"] = "10"
        queries["apiKey"] = API_KEY
        queries["type"] = "snack"
        queries["diet"] = "vegan"
        queries["addRecipeInformation"] = "true"
        queries["fillIngredients"] = "true"

        return queries
    }

    private fun setupRecyclerView() {
        recyclerView.adapter = mAdapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun showShimmerEffect() {
        shimmer.startShimmer()
    }

    private fun hideShimmerEffect() {
        shimmer.hideShimmer()
    }
}