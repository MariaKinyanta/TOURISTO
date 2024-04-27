package org.unh.touristo

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun NavigationUtilisateur (navController: NavHostController) {
    NavHost(navController, startDestination = ScreenUser.DESTINATIONSCREEN.name) {
        composable( route = ScreenUser.DESTINATIONSCREEN.name) {
            DestinationScreen()
        }
        composable(route = ScreenUser.DESTINATIONFAVORITESCREEN.name) {
           DestinationFavoriteScreen()
        }
        composable(route = ScreenUser.HISTORIQUESCREEN.name) {
            HistoriqueScreen()
        }
        composable(route = ScreenUser.MONCOMPTESCREEN.name) {
            MonCompteScreen()
        }
    }
}


enum class ScreenUser{
    DESTINATIONSCREEN,
    DESTINATIONFAVORITESCREEN,
    HISTORIQUESCREEN,
    MONCOMPTESCREEN
}