package com.francotte.apollotest.ui.screens.country_detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.francotte.apollotest.domain.DetailedCountry
import com.francotte.apollotest.domain.GetCountryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class CountryViewModel @Inject constructor(getCountryUseCase: GetCountryUseCase,savedStateHandle: SavedStateHandle): ViewModel() {

    val code = savedStateHandle.toRoute<DetailCountryRoute>().code

    val state: StateFlow<DetailCountryState> = getCountryUseCase
        .invoke(code)
        .map< DetailedCountry?, DetailCountryState> {
            DetailCountryState.Success(it)
        }
        .catch { emit(DetailCountryState.Error) }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), DetailCountryState.Loading)

}

sealed interface DetailCountryState {

    data object Loading : DetailCountryState

    data object Error : DetailCountryState

    data class Success(val country: DetailedCountry?): DetailCountryState

}