package com.francotte.apollotest.ui.screens.country_detail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
data class DetailCountryRoute(val code:String)

fun NavController.navigateToDetailCountry(code:String, navOptions: NavOptionsBuilder.() -> Unit = {}) {
    navigate(route = DetailCountryRoute(code)) {
        navOptions()
    }
}

fun NavGraphBuilder.detailCountryScreen(onNavigationBack: () -> Unit) {
    composable<DetailCountryRoute> {
        CountryRoute(onPopBackStack = onNavigationBack)
    }
}

@Composable
fun CountryRoute(countryViewModel: CountryViewModel= hiltViewModel(), onPopBackStack:()->Unit) {
    val state by countryViewModel.state.collectAsStateWithLifecycle()
    CountryScreen(state,onPopBackStack)
}
