package com.simonjaimesmoncayo.exploracolombiaapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.simonjaimesmoncayo.exploracolombiaapp.ui.theme.ExploraColombiaAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ExploraColombiaAppTheme {
                val myNavController = rememberNavController()
                //mirar si el usuairo esta logueado o no
                val auth = Firebase.auth
                val currentUser = auth.currentUser
                //ruta inicial no se pierda
                var myStartDestination by remember { mutableStateOf("login") }

                if (currentUser != null) {
                    myStartDestination = "home"
                } else {
                    myStartDestination = "login"
                }
                NavHost(
                    navController = myNavController,
                    startDestination = myStartDestination,
                    modifier = Modifier.fillMaxSize()
                ) {
                    composable(route = "login") {
                        LoginScreen(
                            onLoginSuccess = {
                                //aca es que si inicia sesion correctamente
                                myNavController.navigate("home") {
                                    popUpTo("login") { inclusive = true }
                                }
                            },
                            onNavigateToRegister = {
                                myNavController.navigate("register")
                            }
                        )
                    }
                    composable(route = "register") {
                        RegisterScreen(
                            onRegisterSuccess = {
                                // navegar al home y limpiar el historial
                                myNavController.navigate("home") {
                                    popUpTo(0) { inclusive = true } // El popUpTo(0) limpia TODAS las pantallas previas
                                }
                            },
                            onNavigateToLogin = {
                                myNavController.navigate("login")
                            },
                            onBackClick = {
                                myNavController.popBackStack()
                            }
                        )
                    }

                    composable(route = "home") {
                        HomeScreen(
                            onLogout = {
                                // la persona vuelve a login y se borra todo el historial porque pues cerro sesion
                                myNavController.navigate("login") {
                                    popUpTo("home") { inclusive = true }
                                }
                            }
                        )
                    }

                }
            }
        }
    }
}