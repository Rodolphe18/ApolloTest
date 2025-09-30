package com.francotte.apollotest.domain

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class GetCountryUseCase(private val countryClient: CountryClient) {


   operator fun invoke(code:String) : Flow<DetailedCountry?> {
        return flow { emit(countryClient.getCountry(code)) }.flowOn(Dispatchers.IO)
    }
}