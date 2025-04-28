package com.madkit.recipeapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.HtmlCompat
import androidx.fragment.app.viewModels
import com.madkit.recipeapp.databinding.FragmentRecipeDetailBinding
import com.madkit.recipeapp.viewmodel.RecipeViewModel
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import kotlin.getValue

@AndroidEntryPoint
class RecipeDetailFragment : Fragment() {

    private lateinit var binding: FragmentRecipeDetailBinding
    private var selectedId: Int? = null
    val viewModel: RecipeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRecipeDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args = RecipeDetailFragmentArgs.fromBundle(requireArguments())
        selectedId = args.recipeId

        selectedId?.let { id ->
            viewModel.getRecipeDetail(id)
        }

        viewModel.recipeDetailResponse.observe(viewLifecycleOwner) { recipeDetail ->
            (requireActivity() as AppCompatActivity).supportActionBar?.title = recipeDetail.title

            binding.titleRecipe.text = recipeDetail.title
            binding.infoRecipe.text = "${recipeDetail.readyInMinutes} minutes • ${recipeDetail.servings} servings"
            binding.summaryRecipe.text = HtmlCompat.fromHtml(recipeDetail.summary, HtmlCompat.FROM_HTML_MODE_LEGACY)
            binding.ingredientsList.text = recipeDetail.extendedIngredients.joinToString("\n") { "- ${it.original}" }
            binding.instructionsText.text = HtmlCompat.fromHtml(recipeDetail.instructions, HtmlCompat.FROM_HTML_MODE_LEGACY)

            Picasso.get().load(recipeDetail.image).into(binding.imageRecipe)
        }
    }

}