package com.francotte.apollotest.ui.screens.countries

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.francotte.apollotest.domain.GetCountriesUseCase
import com.francotte.apollotest.domain.GetCountryUseCase
import com.francotte.apollotest.domain.LightCountry
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class CountriesViewModel @Inject constructor(private val getCountriesUseCase: GetCountriesUseCase, private val getCountryUseCase: GetCountryUseCase):
    ViewModel() {

        val uiState: StateFlow<CountriesState> = getCountriesUseCase
            .invoke()
            .map<List<LightCountry>,CountriesState> {
                CountriesState.Success(it)
            }
            .catch { emit(CountriesState.Error) }
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), CountriesState.Loading)


}

sealed interface CountriesState {
    data object Loading: CountriesState
    data class Success(val countries:List<LightCountry>): CountriesState
    data object Error: CountriesState
}
