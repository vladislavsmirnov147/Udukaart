package com.example.udukaart

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun StepCounter(modifier: Modifier = Modifier) {
    var steps by remember { mutableStateOf(0) }

    Button(onClick = { steps++ }, modifier = modifier) {
        Text("Samme: $steps")
    }
}

@Composable
fun MeterCounter(modifier: Modifier = Modifier) {
    var meters by remember { mutableStateOf(0.0) }

    Button(onClick = { meters += 10.0 }, modifier = modifier) {
        Text("Meetreid: $meters")
    }
}

@Composable
fun CitySearch(modifier: Modifier = Modifier) {
    var query by rememberSaveable { mutableStateOf("") }

    Column(modifier = modifier.padding(16.dp)) {
        OutlinedTextField(
            value = query,
            onValueChange = { query = it },
            label = { Text("Otsi linna") }
        )
        Text("Otsitakse: $query")
    }
}
