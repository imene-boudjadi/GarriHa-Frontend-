package com.example.login


import android.service.autofill.UserData
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.parkir.views.router.Router

@Composable
fun HomeView(navController: NavHostController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Home Page")
        Button(onClick = {
            if (!isConnected) {
                navController.navigate(Router.AuthScreen.route)
            } else {
                navController.navigate(Router.ReservationsScreen.route)
            }
        }) {
            Text(text = "Go To Reservations")
        }
        Button(onClick = {

                navController.navigate(Router.ParkingScreen.route)

        }) {
            Text(text = "Go To Parking List")
        }
        Button(onClick = {

            navController.navigate(Router.SignUpScreen.route)

        }) {
            Text(text = "Create an account")
        }
    }
}