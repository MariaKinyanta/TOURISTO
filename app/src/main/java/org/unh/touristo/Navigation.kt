package org.unh.touristo

import AdminProfileScreen
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(navController, startDestination = Screen.ADMINLIEUSCREEN.name) {
        composable( route = Screen.ADMINLIEUSCREEN.name) {
            AdminLieuScreen()
        }
        composable(route = Screen.ADMINHEBERGEMENTSCREEN.name) {
            AdminHebergementScreen()
        }
        composable(route = Screen.ADMINRESTSCREEN.name) {
            AdminRestScreen()
        }
        composable(route = Screen.ADMINPROFILE.name) {
            AdminProfileScreen()
        }
    }
}


enum class Screen{
    ADMINLIEUSCREEN,
    ADMINRESTSCREEN,
    ADMINHEBERGEMENTSCREEN,
    ADMINPROFILE
}