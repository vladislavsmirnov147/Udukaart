package com.example.udukaart

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Slider
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.udukaart.R
import com.example.udukaart.ui.theme.UdukaartTheme
import java.util.Locale

@Composable
fun DiscoverySettings(
    enabled: Boolean,
    onEnabledChange: (Boolean) -> Unit,
    minAccuracy: Float,
    onMinAccuracyChange: (Float) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 48.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = stringResource(R.string.discovery_enabled),
                modifier = Modifier.weight(1f)
            )

            Switch(
                checked = enabled,
                onCheckedChange = onEnabledChange
            )
        }

        Text(
            text = stringResource(
                R.string.discovery_accuracy,
                String.format(Locale.ROOT, "%.1f", minAccuracy)
            )
        )

        Slider(
            value = minAccuracy,
            onValueChange = onMinAccuracyChange,
            valueRange = 5f..50f,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun DiscoverySettingsDefaultPreview() {
    UdukaartTheme {
        DiscoverySettings(
            enabled = true,
            onEnabledChange = {},
            minAccuracy = 15f,
            onMinAccuracyChange = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun DiscoverySettingsDisabledPreview() {
    UdukaartTheme {
        DiscoverySettings(
            enabled = false,
            onEnabledChange = {},
            minAccuracy = 25f,
            onMinAccuracyChange = {}
        )
    }
}

@Preview(
    showBackground = true,
    fontScale = 1.5f
)
@Composable
private fun DiscoverySettingsLargeFontPreview() {
    UdukaartTheme {
        DiscoverySettings(
            enabled = true,
            onEnabledChange = {},
            minAccuracy = 40f,
            onMinAccuracyChange = {}
        )
    }
}
