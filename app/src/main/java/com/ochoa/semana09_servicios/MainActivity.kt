package com.ochoa.semana09_servicios

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.ochoa.semana09_servicios.screen.UserListScreen
import com.ochoa.semana09_servicios.ui.theme.Semana09_serviciosTheme
import androidx.activity.enableEdgeToEdge

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Semana09_serviciosTheme {
                UserListScreen()
            }
        }
    }
}