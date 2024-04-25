package com.example.parkir.views.router

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.login.AuthView
import com.example.login.HomeView
import com.example.login.ReservationView

@Composable
fun NavigationHost(navController: NavHostController) {

    NavHost(navController = navController, startDestination = Router.HomeScreen.route) {
        composable(route = Router.HomeScreen.route) {
            HomeView(navController = navController)
        }
        composable(route = Router.AuthScreen.route) {
            AuthView(navController = navController)
        }
        composable(route = Router.ReservationsScreen.route) {
            ReservationView(navController = navController)
        }
    }
}