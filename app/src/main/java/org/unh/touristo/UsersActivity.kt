package org.unh.touristo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import org.unh.touristo.login.LoginScreen
import org.unh.touristo.login.LoginViewModel
import org.unh.touristo.ui.theme.TouristoTheme

class UsersActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TouristoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
//                    LoginScreen(
//                        onNavTochoixVilleActivity = {},
//                        onNavToFormActivity = {})
//

                   // AdminScreen()

                    UsersScreen()

                }
            }
        }
    }
}

@Composable
fun UsersScreen() {
    val navController= rememberNavController()
    val usersScreens= listOf(DestinationScreen(),DestinationFavoriteScreen(),HistoriqueScreen(),MonCompteScreen())
    Scaffold (bottomBar = { NavigationBar {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        NavigationBarItem(selected =currentRoute==ScreenUser.DESTINATIONSCREEN.name, label = { Text(text = "Home")}, onClick = { navController.navigate(ScreenUser.DESTINATIONSCREEN.name) {
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
        } }, icon = { Icon(imageVector = Icons.Default.Home, contentDescription =null ) })

        NavigationBarItem(selected =currentRoute==ScreenUser.DESTINATIONFAVORITESCREEN.name,label = { Text(text = "Favoris")}, onClick = {  navController.navigate(ScreenUser.DESTINATIONFAVORITESCREEN.name) {
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
        }}, icon = { Icon(imageVector = Icons.Default.Favorite, contentDescription =null ) })

        NavigationBarItem(selected =currentRoute==ScreenUser.HISTORIQUESCREEN.name,label = { Text(text = "Historique")}, onClick = { navController.navigate(ScreenUser.HISTORIQUESCREEN.name) {
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
        } }, icon = { Icon(imageVector = Icons.Default.Star, contentDescription =null ) })

        NavigationBarItem(selected =currentRoute==ScreenUser.MONCOMPTESCREEN.name,label = { Text(text = "Mon compte")}, onClick = { navController.navigate(ScreenUser.MONCOMPTESCREEN.name) {
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

    }
    }, content = {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(paddingValues = it)
        ) {
            NavigationUtilisateur(navController = navController)
        }

    })
}

