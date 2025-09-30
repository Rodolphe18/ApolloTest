package com.francotte.apollotest.domain

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class GetCountriesUseCase(private val countryClient: CountryClient) {


   operator fun invoke() : Flow<List<LightCountry>> {
        return flow { emit(countryClient.getCountries().sortedBy { it.name }) }.flowOn(Dispatchers.IO)
    }
}