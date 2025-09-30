package com.francotte.apollotest.domain


interface CountryClient {
   suspend fun getCountries():List<LightCountry>
   suspend fun getCountry(code:String) : DetailedCountry?
}

