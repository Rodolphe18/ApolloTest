package com.francotte.apollotest.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.francotte.apollotest.ui.screens.countries.BASE_ROUTE
import com.francotte.apollotest.ui.screens.countries.countriesScreen
import com.francotte.apollotest.ui.screens.country_detail.detailCountryScreen
import com.francotte.apollotest.ui.screens.country_detail.navigateToDetailCountry

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {

    NavHost(
        navController = navController,
        startDestination = BASE_ROUTE,
        modifier = modifier,
    ) {
        countriesScreen(navController::navigateToDetailCountry)
        detailCountryScreen(navController::popBackStack)
    }
}