package com.example.clock

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.clock.ui.theme.ClockTheme
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ClockTheme {
                Box (
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = androidx.compose.ui.Alignment.Center
                ){
                    val milliseconds = remember {
                        System.currentTimeMillis()
                    }
                    var seconds by remember {
                        mutableStateOf((milliseconds / 1000f )% 60f)
                    }
                    var minutes by remember {
                        mutableStateOf(( milliseconds / 1000f / 60f) % 60f)
                    }
                    var hours by remember {
                        mutableStateOf((milliseconds / 1000f ) /3600f )
                    }
                    LaunchedEffect(key1 = seconds) {
                        delay(1000L)
                        minutes += 1f / 60f
                        hours += 1f / (60f * 12f)
                        seconds += 1f
                    }
                    Clock(
                        seconds = seconds,
                        minutes = minutes,
                        hours = hours
                    )
                }
            }
        }
    }
}
