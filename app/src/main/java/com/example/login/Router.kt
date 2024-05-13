package com.example.parkir.views.router

sealed class Router(val route: String) {
    object AuthScreen: Router("/auth")
    object HomeScreen: Router("/home")
    object ReservationsScreen: Router("/reservation")
    object ParkingScreen: Router("/parking")
    object SignUpScreen: Router("/signup")
    object ReservationScreen: Router("/createReservation")
}