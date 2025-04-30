package com.example.fitnessapp3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.*
import com.example.fitnessapp3.ui.screens.HomeScreen
import com.example.fitnessapp3.ui.screens.SignInScreen
import com.example.fitnessapp3.ui.theme.Fitnessapp3Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            //firce dark them
            Fitnessapp3Theme(darkTheme = true) {
                val navController = rememberNavController()
                //sing in screen
                NavHost(navController, startDestination = "signin") {
                    composable("signin") {
                        SignInScreen {
                            navController.navigate("home")
                        }
                    }//home
                    composable("home") {
                        HomeScreen(context = this@MainActivity)
                    }
                }
            }
        }
    }
}