package com.simonjaimesmoncayo.exploracolombiaapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.simonjaimesmoncayo.exploracolombiaapp.ui.theme.ExploraColombiaAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ExploraColombiaAppTheme {
                val myNavController = rememberNavController()
                NavHost(
                    navController = myNavController,
                    startDestination = "login",
                    modifier = Modifier.fillMaxSize()
                ) {
                    composable(route = "login") {
                        LoginScreen(
                            onLoginSuccess = { },
                            onNavigateToRegister = {
                                myNavController.navigate("register")
                            }
                        )
                    }
                    composable(route = "register") {
                        RegisterScreen(
                            onRegisterSuccess = {
                                myNavController.navigate("login") {
                                    popUpTo("login") { inclusive = true }
                                }
                            },
                            onNavigateToLogin = {
                                myNavController.navigate("login") {
                                    popUpTo("login") { inclusive = true }
                                }
                            },
                            onBackClick = {
                                myNavController.popBackStack()
                            }
                        )
                    }
                }
            }
        }
    }
}