package com.example.login
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.login.retrofit.CreateReservationResponse
import com.example.login.retrofit.Endpoint
import com.example.login.retrofit.ReservationData
import com.example.login.retrofit.SignUpResponse
import com.maxkeppeker.sheets.core.models.base.rememberSheetState
import com.maxkeppeler.sheets.calendar.CalendarDialog
import com.maxkeppeler.sheets.calendar.models.CalendarConfig
import com.maxkeppeler.sheets.calendar.models.CalendarSelection
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import java.time.LocalDateTime
import androidx.compose.material3.Button as Button1


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReservationCreateView(navController: NavHostController) {
    val context = LocalContext.current
    var DescriptionReservation by remember { mutableStateOf("") }

    var iduser by remember { mutableStateOf("") }
    var idparking by remember { mutableStateOf("") }
    var dateDebut by remember { mutableStateOf(LocalDateTime.now()) }
    var dateFin by remember { mutableStateOf(LocalDateTime.now()) }
    var response by remember { mutableStateOf<Response<CreateReservationResponse>?>(null) }
    // Définir une liste de noms
    val names = listOf("Name 1", "Name 2", "Name 3")
    // État pour stocker le nom sélectionné
    val selectedName = remember { mutableStateOf<String?>(null) }

    var showDialogReservation by remember { mutableStateOf(false) }

    val calendarState = rememberSheetState()
    CalendarDialog(
        state = calendarState ,
        config = CalendarConfig(
            monthSelection = true,
            yearSelection = true
        ),
        selection = CalendarSelection.Date { dateDebut ->

            //dateFin = end
            Log.d("SelectedDates", "Start: $dateDebut")
        }
    )



    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Create Reservation", style = MaterialTheme.typography.bodyMedium)

        TextField(
            value = DescriptionReservation,
            onValueChange = { DescriptionReservation = it },
            label = { Text("Reservation Description") },
            modifier = Modifier.padding(vertical = 8.dp)
        )
        TextField(
            value = iduser,
            onValueChange = { iduser = it },
            label = { Text("Reservation User") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            modifier = Modifier.padding(vertical = 8.dp)
        )
        TextField(
            value = idparking,
            onValueChange = { idparking = it },
            label = { Text("Reservation Parking") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            modifier = Modifier.padding(vertical = 8.dp)
        )


        Button1(
            onClick = {
              calendarState.show()
                      }) {
            Text(text = "Choose start Date")
        }
        Button1(
            onClick = {
                calendarState.show()
            }) {
            Text(text = "Choose second Date")
        }
        // Liste déroulante pour choisir un nom
     /*   DropdownMenu(
            expanded = showDialogReservation,
            onDismissRequest = { showDialogReservation = false }
        ) {
            names.forEach { name ->
                DropdownMenuItem(onClick = {
                    selectedName.value = name
                    showDialogReservation = false
                }) {
                    Text(name)
                }
            }
        }
*/

        Button1(
            onClick = {
                if (validateFields(
                        DescriptionReservation,
                        iduser.toString(),
                        idparking.toString()

                    )
                ) {
                    // If fields are valid, proceed with creation
                    val reservationData = ReservationData(
                        DescriptionReservation = DescriptionReservation,
                        iduser = iduser,
                        idparking = idparking,
                        dateDebut = dateDebut.toString(),//transformer la date retournee par Calendar Dialog
                        dateFin = dateFin.toString()//transforme l adresse retournee par calender Dialog
                    )

                    // Call the sign-up endpoint
                    CoroutineScope(Dispatchers.IO).launch {
                        val createReservationResponse = try {
                            Endpoint.createEndpoint().createReservation(reservationData)
                        } catch (e: Exception) {
                            null
                        }

                        // Update response state
                        response = createReservationResponse
                        showDialogReservation = true // Corrected the syntax

                    }
                } else {
                    Toast.makeText(
                        context,
                        "Please fill all fields and ensure passwords match",
                        Toast.LENGTH_SHORT
                    ).show()
                }

            },
            modifier = Modifier.padding(vertical = 16.dp)

        )  {
            Text(text = "Create Reservation")
        }
    }
}

private fun validateFields(Description: String, idUser: String, idParking: String): Boolean {
    // Basic validation - ensure all fields are filled and passwords match
    return Description.isNotBlank() && idUser.toString().isNotBlank() && idParking.toString().isNotBlank()
}
