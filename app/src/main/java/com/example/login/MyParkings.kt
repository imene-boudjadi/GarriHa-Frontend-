package com.example.login
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.login.Parking
import com.example.login.UserDatabase
import com.example.parkir.views.router.Router
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext

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
        Spacer(modifier = Modifier.height(16.dp))

        if (parkings.isEmpty()) {
            Text(text = "No Parkings Found")
        } else {
            LazyColumn {
                items(parkings) { parking ->
                    ParkingListItem(parking = parking)
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }
}

@Composable
fun ParkingListItem(parking: Parking) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .height(100.dp)
            .border(1.dp, Color.Red)
            .padding(8.dp)
    ) {
        Box(
            modifier = Modifier
                .width(100.dp)
                .aspectRatio(1f)
        ) {
            // Placeholder image for parking
            // You can replace this with actual image loading logic
            Text(
                text = "Image",
                modifier = Modifier.fillMaxSize(),
                textAlign = TextAlign.Center
            )
        }
        Spacer(modifier = Modifier.width(8.dp))
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
                .padding(vertical = 4.dp)
        ) {
            Text(
                text = parking.parkingName,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 4.dp)
            )
            Text(
                text = parking.DescriptionParking,
                modifier = Modifier.weight(1f)
            )
        }
    }
}
