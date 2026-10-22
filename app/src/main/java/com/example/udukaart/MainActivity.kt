package com.example.udukaart

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.udukaart.ui.theme.UdukaartTheme

class MainActivity : ComponentActivity() {

    private val walkDetection = DemoWalkDetection()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("Udukaart", "onCreate")

        enableEdgeToEdge()

        setContent {
            UdukaartTheme {
                var discoveryEnabled by rememberSaveable {
                    mutableStateOf(true)
                }

                var minAccuracy by rememberSaveable {
                    mutableFloatStateOf(15f)
                }

                LaunchedEffect(discoveryEnabled, minAccuracy) {
                    Log.d(
                        "Udukaart",
                        "Discovery enabled=$discoveryEnabled, accuracy=$minAccuracy"
                    )
                }

                DisposableEffect(discoveryEnabled, walkDetection) {
                    if (discoveryEnabled) {
                        walkDetection.subscribe {
                            Log.d("Udukaart", "Walk detected")
                        }
                    }

                    onDispose {
                        walkDetection.unsubscribe()
                    }
                }

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(
                        modifier = Modifier
                            .padding(innerPadding)
                            .verticalScroll(rememberScrollState())
                    ) {
                        MapRoute()

                        DiscoverySettings(
                            enabled = discoveryEnabled,
                            onEnabledChange = { discoveryEnabled = it },
                            minAccuracy = minAccuracy,
                            onMinAccuracyChange = { minAccuracy = it }
                        )
                    }
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d("Udukaart", "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("Udukaart", "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("Udukaart", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("Udukaart", "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("Udukaart", "onDestroy")
    }
}

@Preview(showBackground = true)
@Composable
private fun MainScreenPreview() {
    UdukaartTheme {
        Column {
            MapRoute()

            DiscoverySettings(
                enabled = true,
                onEnabledChange = {},
                minAccuracy = 15f,
                onMinAccuracyChange = {}
            )
        }
    }
}
