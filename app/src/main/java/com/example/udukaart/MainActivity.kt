package com.example.udukaart

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.udukaart.ui.theme.UdukaartTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("Udukaart", "onCreate")

        enableEdgeToEdge()

        setContent {
            UdukaartTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        StepCounter()
                        MeterCounter()
                        CitySearch()
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

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Tere tulemast Udukaarti!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    UdukaartTheme {
        Greeting("Android")
    }
}
