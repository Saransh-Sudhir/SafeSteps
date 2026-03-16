package com.safesteps.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.safesteps.app.navigation.SafeStepsNavigation
import com.safesteps.app.ui.theme.SafeStepsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SafeStepsTheme {
                SafeStepsNavigation()
            }
        }
    }
}