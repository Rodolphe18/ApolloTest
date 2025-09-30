package com.francotte.apollotest.ui.screens.country_detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun CountryScreen(detailCountryState: DetailCountryState, onPopBackStack:()->Unit) {

    when (detailCountryState) {
        DetailCountryState.Error -> Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("UNKNOWN ERROR")
        }
        DetailCountryState.Loading -> Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
        is DetailCountryState.Success -> Column(Modifier.padding(16.dp)) {
            detailCountryState.country?.let {
                Text(it.name, fontSize = 26.sp, fontWeight = FontWeight.SemiBold)
                Spacer(Modifier.height(6.dp))
                Text("Continent: ${it.continent} ", fontSize = 16.sp)
                Spacer(Modifier.height(6.dp))
                Text("Capital: ${it.capital}", fontSize = 16.sp)
                Spacer(Modifier.height(6.dp))
                Row {
                    Text("Languages:", fontSize = 16.sp)
                    it.languages.forEach {language ->
                        Text(" ${language}, ", fontSize = 14.sp)
                    }
                }

            }
        }
    }


}