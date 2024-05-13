package com.example.parkir.views.router


import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.login.AuthView

import com.example.login.HomeView
import com.example.login.ParkingView
import com.example.login.ReservationCreateView

import com.example.login.ReservationView
import com.example.login.SignUpView

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
        composable(route = Router.ParkingScreen.route) {
            ParkingView(navController = navController)
        }
        composable(route = Router.SignUpScreen.route) {
            SignUpView(navController = navController)
        }
        composable(route = Router.ReservationScreen.route ){
            ReservationCreateView(navController = navController)
        }
    }
}