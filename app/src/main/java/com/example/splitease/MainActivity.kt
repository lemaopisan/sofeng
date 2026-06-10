package com.example.splitease

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.splitease.navigation.AppNavigation
import com.example.splitease.navigation.Screen

import com.example.splitease.ui.theme.SplitEaseTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        val sharedPref = getSharedPreferences("SplitEasePrefs", android.content.Context.MODE_PRIVATE)
        val userName = sharedPref.getString("user_name", null)

        setContent {
            SplitEaseTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavigation(startDestination = Screen.Splash.route)
                }
            }
        }
    }
}
