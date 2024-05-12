package com.example.login

import android.content.Context
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.edit
import androidx.navigation.NavHostController
import com.example.parkir.views.router.Router
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun ReservationView(navController: NavHostController) {
    val context = LocalContext.current

    val reservationDao = UserDatabase.getDatabase(context).getReservationDao()
    var reservations: List<Reservation> = reservationDao.getAllReservations()

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(onClick = {
            isConnected = false
            val prefs =
                context.getSharedPreferences(
                    "prefs", Context.MODE_PRIVATE
                )

            prefs.edit {
                putBoolean(
                    "isConnected", false
                )
            }
            navController.navigateUp()
        }) {
            Text(text = "Logout")
        }
        Text(text = "Reservation Page")
//        for (i in 1..10) {
//            Text(
//                text = "Reservation $i",
//                modifier = Modifier
//                    .padding(5.dp, 5.dp)
//                    .fillMaxWidth()
//                    .border(
//                        1.dp, Color.Black, shape = RoundedCornerShape(size = 5.dp)
//                    )
//                    .padding(20.dp, 10.dp)
//            )
//        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        ) {
            if (reservations.isEmpty()) {
                Text(
                    text ="No reservations found",
                    modifier = Modifier.align(Alignment.Center)
                )
            } else {
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(reservations) { reservation ->
                        ReservationListItem(reservation) // Replace with your reservation view composable
                    }
                }
            }
        }

    }
}

@Composable
fun ReservationListItem(reservation: Reservation) {
    Text(
        text = "Reservation ID: ${reservation.reservationId}",
        modifier = Modifier
            .padding(5.dp, 5.dp)
            .fillMaxWidth()
            .border(
                1.dp, Color.Black, shape = RoundedCornerShape(size = 5.dp)
            )
            .padding(20.dp, 10.dp)
    )
}