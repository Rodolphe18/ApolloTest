package com.francotte.apollotest.ui.screens.countries

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation


const val HOME_ROUTE = "home"
const val BASE_ROUTE = "base"


fun NavGraphBuilder.countriesScreen(
    onItemClick: (String) -> Unit
) {
    navigation(startDestination = HOME_ROUTE, route = BASE_ROUTE) {
        composable(route = HOME_ROUTE) {
           CountriesRoute { onItemClick(it) }
        }
    }
}

@Composable
fun CountriesRoute(countriesViewModel: CountriesViewModel= hiltViewModel(), onItemClick:(String)->Unit) {
    val state by countriesViewModel.uiState.collectAsStateWithLifecycle()
    CountriesScreen(state, onItemClick)
}