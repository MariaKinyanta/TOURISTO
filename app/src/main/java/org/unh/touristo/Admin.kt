package org.unh.touristo

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AdminScreen() {
    val navController= rememberNavController()
    val adminScreens= listOf(AdminLieuScreen(),AdminHebergementScreen(),AdminRestScreen())
    Scaffold (bottomBar = { NavigationBar {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        NavigationBarItem(selected =currentRoute==Screen.ADMINLIEUSCREEN.name, label = { Text(text = "Destination")}, onClick = { navController.navigate(Screen.ADMINLIEUSCREEN.name) {
            // Pop up to the start destination of the graph to
            // avoid building up a large stack of destinations
            // on the back stack as users select items
            navController.graph.startDestinationRoute?.let { route ->
                popUpTo(route) {
                    saveState = true
                }
            }
            // Avoid multiple copies of the same destination when
            // reselecting the same item
            launchSingleTop = true
            // Restore state when reselecting a previously selected item
            restoreState = true
        } }, icon = { Icon(imageVector = Icons.Default.Place, contentDescription =null ) })

        NavigationBarItem(selected =currentRoute==Screen.ADMINHEBERGEMENTSCREEN.name,label = { Text(text = "Logement")}, onClick = {  navController.navigate(Screen.ADMINHEBERGEMENTSCREEN.name) {
            // Pop up to the start destination of the graph to
            // avoid building up a large stack of destinations
            // on the back stack as users select items
            navController.graph.startDestinationRoute?.let { route ->
                popUpTo(route) {
                    saveState = true
                }
            }
            // Avoid multiple copies of the same destination when
            // reselecting the same item
            launchSingleTop = true
            // Restore state when reselecting a previously selected item
            restoreState = true
        }}, icon = { Icon(imageVector = Icons.Default.Home, contentDescription =null ) })

        NavigationBarItem(selected =currentRoute==Screen.ADMINRESTSCREEN.name,label = { Text(text = "Restaurant")}, onClick = { navController.navigate(Screen.ADMINRESTSCREEN.name) {
            // Pop up to the start destination of the graph to
            // avoid building up a large stack of destinations
            // on the back stack as users select items
            navController.graph.startDestinationRoute?.let { route ->
                popUpTo(route) {
                    saveState = true
                }
            }
            // Avoid multiple copies of the same destination when
            // reselecting the same item
            launchSingleTop = true
            // Restore state when reselecting a previously selected item
            restoreState = true
        } }, icon = { Icon(imageVector = Icons.Default.Favorite, contentDescription =null ) })

        NavigationBarItem(selected =currentRoute==Screen.ADMINPROFILE.name,label = { Text(text = "Profil")}, onClick = { navController.navigate(Screen.ADMINPROFILE.name) {
            // Pop up to the start destination of the graph to
            // avoid building up a large stack of destinations
            // on the back stack as users select items
            navController.graph.startDestinationRoute?.let { route ->
                popUpTo(route) {
                    saveState = true
                }
            }
            // Avoid multiple copies of the same destination when
            // reselecting the same item
            launchSingleTop = true
            // Restore state when reselecting a previously selected item
            restoreState = true
        } }, icon = { Icon(imageVector = Icons.Default.AccountCircle, contentDescription =null ) })

    }}, content = {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(paddingValues = it)
        ) {
            Navigation(navController = navController)
        }
    
    })

}