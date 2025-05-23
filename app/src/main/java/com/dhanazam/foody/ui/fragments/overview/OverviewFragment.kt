package com.dhanazam.foody.ui.fragments.overview

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import coil.load
import com.dhanazam.foody.R
import com.dhanazam.foody.bindingadapters.RecipesRowBinding
import com.dhanazam.foody.databinding.FragmentOverviewBinding
import com.dhanazam.foody.models.Result
import com.dhanazam.foody.util.Constants.Companion.RECIPE_RESULT_KEY
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OverviewFragment : Fragment() {

    private var _binding: FragmentOverviewBinding? = null
    private val binding get() = _binding!!

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentOverviewBinding.inflate(inflater, container, false)

        val args = arguments
        val myBundle: Result? = args?.getParcelable(RECIPE_RESULT_KEY, Result::class.java)

        if (myBundle != null) {
            binding.mainImageView.load(myBundle.image)
            binding.titleTextView.text = myBundle.title
            binding.likesTextView.text = myBundle.aggregateLikes.toString()
            binding.timeTextView.text = myBundle.readyInMinutes.toString()
            RecipesRowBinding.parseHtml(binding.summaryTextView, myBundle.summary)

            updateColors(myBundle.vegetarian, binding.vegetarianTextView, binding.vegetarianImageView)
            updateColors(myBundle.vegan, binding.veganTextView, binding.veganImageView)
            updateColors(myBundle.cheap, binding.cheapTextView, binding.cheapImageView)
            updateColors(myBundle.dairyFree, binding.dairyFreeTextView, binding.dairyFreeImageView)
            updateColors(myBundle.glutenFree, binding.glutenFreeTextView, binding.glutenFreeImageView)
            updateColors(myBundle.veryHealthy, binding.healthyTextView, binding.healthyImageView)
        }

        return binding.root
    }

    private fun updateColors(stateIsOn: Boolean, textView: TextView, imageView: ImageView) {
        if (stateIsOn) {
            imageView.setColorFilter(ContextCompat.getColor(requireContext(),R.color.green))
            textView.setTextColor(ContextCompat.getColor(requireContext(), R.color.green))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}