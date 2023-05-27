package com.example.navigationbasiccompose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.traceEventEnd
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode.Companion.Screen
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun HomeScreen(navController: NavHostController) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = Modifier.clickable {
                navController.navigate(route = DisplayScreen.Detail.route)
            },
            text = "Home",
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun DetailScreen(navController: NavHostController) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = Modifier.clickable {
                navController.navigate(DisplayScreen.Home.route) {
                    popUpTo(DisplayScreen.Home.route) {
                        inclusive = true
                    }
                }

                //navController.popBackStack()
            },
            text = "Detail",
            color = Color.Red,
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun SetupNabGraph(navController: NavHostController) {
    NavHost(
            navController = navController,
            startDestination = DisplayScreen.Home.route
    ) {
         composable(
             route = DisplayScreen.Home.route
         ) {
             HomeScreen(navController = navController)
         }

        composable(
            route = DisplayScreen.Detail.route
        ) {
            DetailScreen(navController = navController)
        }
    }
}

sealed class DisplayScreen(val route: String) {
    object Home: DisplayScreen(route = "home_screen")
    object Detail: DisplayScreen(route = "detail_screen")
}