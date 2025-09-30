package com.francotte.apollotest.domain

import com.francotte.CountriesQuery
import com.francotte.CountryQuery

data class LightCountry(val code: String, val name: String, val emoji: String, val capital: String?)

class DetailedCountry(val code: String, val name: String, val emoji: String, val capital: String?, val currency:String?, val languages:List<String>,val continent:String?)

data class Language(val name: String)

data class Continent(val name: String)

fun CountriesQuery.Country.asLightExternalModel() = LightCountry(code,name,emoji,capital)

fun CountryQuery.Country.asDetailedExternalModel() = DetailedCountry(code, name,emoji,capital,currency,languages.map { it.asExternalLanguage().name },continent.asExternalContinent().name)

fun CountryQuery.Language.asExternalLanguage() = Language(name)

fun CountryQuery.Continent.asExternalContinent() = Continent(name)