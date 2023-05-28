package com.example.navigationbasiccompose

import android.util.Log
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
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun HomeScreen(navController: NavHostController) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = Modifier.clickable {
                navController.navigate(route = DisplayScreen.Detail.passNameAndId(
                    id = 567,
                    name = "jeypore"
                ))
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
            route = DisplayScreen.Detail.route,
            arguments = listOf(
                navArgument(DETAIL_ARGUMENT_KEY) {
                type = NavType.IntType
                defaultValue = 0
            },
                navArgument(DETAIL_ARGUMENT_KEY2){
                    type = NavType.StringType
                }
            )
        ) {
            Log.d("Args ", it.arguments?.getInt(DETAIL_ARGUMENT_KEY).toString())
            Log.d("Args ", it.arguments?.getString(DETAIL_ARGUMENT_KEY2).toString())
            DetailScreen(navController = navController)
        }
    }
}

const val DETAIL_ARGUMENT_KEY = "id"
const val DETAIL_ARGUMENT_KEY2 = "name"

sealed class DisplayScreen(val route: String) {
    object Home: DisplayScreen(route = "home_screen")


    //Required Argument
    /*object Detail: DisplayScreen(route = "detail_screen/{$DETAIL_ARGUMENT_KEY}/{$DETAIL_ARGUMENT_KEY2}"){
        *//*fun passId(id: Int): String {
            //return "detail_screen/$id"
            return this.route.replace(oldValue = "{$DETAIL_ARGUMENT_KEY}", newValue = id.toString())
        }*//*

        fun passNameAndId(
            id: Int,
            name: String
        ): String {
            return "detail_screen/$id/$name"
        }
    }*/

    //Optional Argument
    object Detail: DisplayScreen(route = "detail_screen?id={id}&name={name}"){
       /* fun passId(id: Int = 0): String {
            return "detail_screen?id={id}"
        }*/

        fun passNameAndId(
            id: Int = 0,
            name: String = "nayak"
        ): String {
            return "detail_screen?id=$id&name=$name"
        }
    }
}

