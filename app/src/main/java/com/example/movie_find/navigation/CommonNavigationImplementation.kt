package com.example.movie_find.navigation

import androidx.core.os.bundleOf
import androidx.navigation.NavController
import com.example.base.navigation.CommonNavigation
import com.example.domain.entities.SearchResults
import com.example.home.DetailFragment
import com.example.movie_find.R

class CommonNavigationImplementation : CommonNavigation {
    override fun navigateToDetail(findNavController: NavController, data: Any) {
        if (data is SearchResults) {
            val bundle = bundleOf(Pair(DetailFragment.PARAM_DETAIL_MOVIE, data))
            findNavController.navigate(R.id.action_mainFrament_to_detailFragment, bundle)
        }
    }
}