package com.francotte.apollotest.di

import android.content.Context
import com.apollographql.apollo3.ApolloClient
import com.francotte.apollotest.data.ApolloCountryClient
import com.francotte.apollotest.domain.CountryClient
import com.francotte.apollotest.domain.GetCountriesUseCase
import com.francotte.apollotest.domain.GetCountryUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import java.io.File
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesApolloClient(): ApolloClient {
        return ApolloClient.Builder().serverUrl("https://countries.trevorblades.com/graphql").build()
    }

    @Provides
    @Singleton
    fun providesCountryClient(apolloClient: ApolloClient): CountryClient{
        return ApolloCountryClient(apolloClient)
    }

    @Provides
    @Singleton
    fun providesGetCountriesUseCase(countryClient: CountryClient): GetCountriesUseCase {
       return GetCountriesUseCase(countryClient)
    }

    @Provides
    @Singleton
    fun providesGetDetailCountryUseCase(countryClient: CountryClient): GetCountryUseCase {
        return GetCountryUseCase(countryClient)
    }



}