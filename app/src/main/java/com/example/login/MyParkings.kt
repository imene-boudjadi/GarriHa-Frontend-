package com.example.login

import android.content.Context
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.navigation.NavHostController

import com.example.parkir.views.router.Router
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import androidx.compose.foundation.border
import androidx.compose.foundation.shape.RoundedCornerShape

@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun ParkingView(navController: NavHostController) {
    val context = LocalContext.current

    val parkingFlow = remember {
        flow {
            val parkingDao = UserDatabase.getDatabase(context).getParkingDao()
            val parkings = withContext(Dispatchers.IO) {
                parkingDao.getAllParkings()
            }
            emit(parkings)
        }
    }

    var parkings by remember { mutableStateOf(emptyList<Parking>()) }

    LaunchedEffect(key1 = parkingFlow) {
        parkingFlow.collect { newParkings ->
            parkings = newParkings
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(text = "Parking List")

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        ) {
            if (parkings.isEmpty()) {
                Text(
                    text = "No Parkings Found",
                    modifier = Modifier.align(Alignment.Center)
                )
            } else {
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(parkings) { parking ->
                        ParkingListItem(parking)
                    }
                }
            }
        }
    }
}

@Composable
fun ParkingListItem(parking: Parking) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 4.dp)
            .border(1.dp, Color.Red, RoundedCornerShape(8.dp)) // Corrected usage
    ){
        Column(modifier = Modifier.fillMaxSize()) {
            Text(
                text = "................hhh",
                modifier = Modifier.weight(1f).padding(8.dp),
                fontWeight = FontWeight.Bold
            )
            Text(
                text = parking.DescriptionParking, // corrected property name
                modifier = Modifier.weight(0.2f).padding(8.dp),
            )
        }
    }
}

@Composable
fun HomePView(navController: NavHostController) {
    val context = LocalContext.current
    val parkingFlow = remember {
        flow {
            val parkingDao = UserDatabase.getDatabase(context).getParkingDao()
            val parkings = withContext(Dispatchers.IO) {
                parkingDao.getAllParkings()
            }
            emit(parkings)
        }
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Home Parking Page")
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
    }
}
