package com.francotte.apollotest.data

import com.apollographql.apollo3.ApolloClient
import com.francotte.apollotest.CountriesQuery
import com.francotte.apollotest.CountryQuery
import com.francotte.apollotest.domain.CountryClient
import com.francotte.apollotest.domain.DetailedCountry
import com.francotte.apollotest.domain.LightCountry
import com.francotte.apollotest.domain.asDetailedExternalModel
import com.francotte.apollotest.domain.asLightExternalModel

class ApolloCountryClient(private val apolloClient: ApolloClient) : CountryClient {
    override suspend fun getCountries(): List<LightCountry> {
        return apolloClient
            .query(CountriesQuery())
            .execute()
            .data
            ?.countries
            ?.map { it.asLightExternalModel() }
            ?: emptyList()
    }

    override suspend fun getCountry(code: String): DetailedCountry? {
       return apolloClient
            .query(CountryQuery(code))
            .execute()
            .data
            ?.country
            ?.asDetailedExternalModel()
    }


}