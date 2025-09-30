package com.francotte.apollotest.ui.screens.countries

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.francotte.apollotest.domain.LightCountry


@Composable
fun CountriesScreen(countriesState: CountriesState,onItemClick:(String)->Unit) {

    when (countriesState) {
        CountriesState.Error -> Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("UNKNOWN ERROR")
        }

        CountriesState.Loading -> {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }

        is CountriesState.Success -> {
            LazyColumn {
                items(countriesState.countries) { country ->
                    CountryRowItem(modifier = Modifier.fillMaxWidth().clickable { onItemClick(country.code) }.padding(12.dp), country = country)
                }
            }
        }

    }
}


@Composable
fun CountryRowItem(modifier: Modifier=Modifier, country: LightCountry) {
    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        Text(text = country.emoji, fontSize = 50.sp)
        Spacer(Modifier.width(12.dp))
        Column {
            Text(country.name, fontSize = 24.sp)
            Spacer(Modifier.height(6.dp))
            Text(country.capital ?: "")
        }
    }
}