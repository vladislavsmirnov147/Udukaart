package com.example.udukaart

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.udukaart.ui.theme.UdukaartTheme
import java.util.Locale

@Composable
fun StreetCard(name: String, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .padding(16.dp)
    ) {
        Text(name)
    }
}

@Composable
fun PhotoTile(
    label: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .size(96.dp)
            .clip(RoundedCornerShape(12.dp))
            .clickable(onClick = onClick)
            .background(MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Text(label)
    }
}

@Composable
fun CoverageBadge(
    percent: Double,
    modifier: Modifier = Modifier
) {
    Text(
        text = "%.1f%%".format(Locale.ROOT, percent),
        modifier = modifier.padding(8.dp)
    )
}

@Composable
fun CityRow(
    city: String,
    percent: Double,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier) {
        Text(text = city)
        Text(text = "%.1f%%".format(Locale.ROOT, percent))
    }
}

@Preview(showBackground = true)
@Composable
private fun StreetCardPreview() {
    UdukaartTheme {
        StreetCard(
            name = "Tartu tänav",
            modifier = Modifier.background(Color.Red)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PhotoTilePreview() {
    UdukaartTheme {
        PhotoTile(
            label = "Foto 1",
            onClick = {},
            modifier = Modifier.background(Color.Red)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun CoverageBadgePreview() {
    UdukaartTheme {
        CoverageBadge(
            percent = 56.8,
            modifier = Modifier.background(Color.Red)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun CityRowPreview() {
    UdukaartTheme {
        CityRow(
            city = "Tallinn",
            percent = 75.0,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Red)
        )
    }
}
