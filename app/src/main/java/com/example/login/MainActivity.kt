//package com.example.login
//
//import android.content.Context
//import android.os.Bundle
//import androidx.activity.ComponentActivity
//import androidx.activity.compose.setContent
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.Surface
//import androidx.compose.runtime.*
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.lifecycle.lifecycleScope
//import androidx.navigation.compose.rememberNavController
//import com.example.login.Parking
//import com.example.login.UserDatabase
//import com.example.login.ui.theme.LoginTheme
//import com.example.parkir.views.router.NavigationHost
//import com.example.parkir.views.router.Router
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.launch
//import kotlinx.coroutines.withContext
//
//class MainActivity : ComponentActivity() {
// override fun onCreate(savedInstanceState: Bundle?) {
// super.onCreate(savedInstanceState)
//
// setContent {
// val context = LocalContext.current
//
// LoginTheme {
// Surface(
// modifier = Modifier.fillMaxSize(),
// color = MaterialTheme.colorScheme.background
// ) {
// val navController = rememberNavController()
//
// // Récupération des parkings depuis la base de données dans un bloc de coroutine
// var parkings by remember { mutableStateOf<List<Parking>>(emptyList()) }
//
//
// LaunchedEffect(key1 = context) {
// val parkingDao = UserDatabase.getDatabase(context).getParkingDao()
//
// withContext(Dispatchers.IO) {
// parkings = parkingDao.getAllParkings()
// if (parkings.isEmpty()) {
// // Ajout de 5 parkings par défaut
// parkingDao.addParking(Parking(parkingName = "Parking 1", DescriptionParking = "Description 1"))
// parkingDao.addParking(Parking(parkingName = "Parking 2", DescriptionParking = "Description 2"))
// parkingDao.addParking(Parking(parkingName = "Parking 3", DescriptionParking = "Description 3"))
// parkingDao.addParking(Parking(parkingName = "Parking 4", DescriptionParking = "Description 4"))
// parkingDao.addParking(Parking(parkingName = "Parking 5", DescriptionParking = "Description 5"))
// parkings = parkingDao.getAllParkings()
// }
// }
// }
//
// // Affichage de la liste des parkings
// DisplayParkings(parkings = parkings)
// }
// }
// }
// }
//
// private suspend fun fetchDataFromDatabase(context: Context): List<Parking> {
// return withContext(Dispatchers.IO) {
// UserDatabase.getDatabase(context).getParkingDao().getAllParkings()
// }
// }
//}
//
//@Composable
//fun DisplayParkingss(parkings: List<Parking>) {
// // Affichage de la liste des parkings ici
// // Utilisez la fonction DisplayParkings fournie dans ListParkings.kt
// DisplayParkings(parkings = parkings)
//}
//
//@Preview(showBackground = true)
//@Composable
//fun PreviewDisplayParkings() {
// val sampleParkings = listOf(
// Parking(parkingId = 1, parkingName = "Parking 1", DescriptionParking = "Description 1"),
// Parking(parkingId = 2, parkingName = "Parking 2", DescriptionParking = "Description 2")
// )
// DisplayParkings(parkings = sampleParkings)
//}

package com.example.login


import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.service.autofill.UserData
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.login.ui.theme.LoginTheme
import com.example.parkir.views.router.NavigationHost
import com.example.parkir.views.router.Router
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

var isConnected = false

class MainActivity : ComponentActivity() {
    @SuppressLint("CoroutineCreationDuringComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//// val userDatabase = UserDatabase.getDatabase(applicationContext)
// setContent {
// val context = LocalContext.current
//
//
////
// val prefs =
// context.getSharedPreferences(
// "prefs", Context.MODE_PRIVATE
// )
// isConnected = prefs.getBoolean(
// "isConnected", false
// )
//
// LoginTheme {
// // A surface container using the 'background' color from the theme
// Surface(
// modifier = Modifier.fillMaxSize(),
// color = MaterialTheme.colorScheme.background
// ) {
// val navController = rememberNavController()
//
// lifecycleScope.launch {
// withContext(Dispatchers.IO){
// val userDao = UserDatabase.getDatabase(context).getUserDao()
//
// // Dummy user data
// val user1 = User(firstName = "imene", lastName = "Boudjadi", email = "ki_boudjadi@esi.dz", motDePasse = "test")
// val user2 = User(firstName = "yasmine", lastName = "Ghouar", email = "ky_ghouar@esi.dz", motDePasse = "test")
//
// // Insert users into the database
// userDao.addUser(user1)
// userDao.addUser(user2)
// }
// }
// NavigationHost(navController = navController)
// }
// }
// }
// }
//}



        setContent {
            val context = LocalContext.current

            val prefs =
                context.getSharedPreferences(
                    "prefs", Context.MODE_PRIVATE
                )
            isConnected = prefs.getBoolean(
                "isConnected", false
            )

            LoginTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()

// One-time user data insertion
                    lifecycleScope.launch {
                        val hasUser= UserDatabase.getDatabase(context).getUserDao().hasUsers()

                        if (!hasUser) { // Insert users only if no users exist
                            withContext(Dispatchers.IO) {
                                val userDao = UserDatabase.getDatabase(context).getUserDao()

// Dummy user data
                                val user1 = User(firstName = "imene", lastName = "Boudjadi", email = "ki_boudjadi@esi.dz", motDePasse = "test")
                                val user2 = User(firstName = "yasmine", lastName = "Ghouar", email = "ky_ghouar@esi.dz", motDePasse = "test")

// Insert users into the database
                                userDao.addUser(user1)
                                userDao.addUser(user2)
                            }
                        }

// One-time parking data insertion (added)

                        withContext(Dispatchers.IO) {
                            val parkingDao = UserDatabase.getDatabase(context).getParkingDao()

// Dummy parking data
                            val parking1 = Parking(parkingName = "Parking Lot A", DescriptionParking = "This is a convenient parking lot near the main entrance.")
                            val parking2 = Parking(parkingName = "Parking Lot B", DescriptionParking = "This parking lot offers covered parking spaces.")

// Insert parkings into the database
                            parkingDao.addParking(parking1)
                            parkingDao.addParking(parking2)
                        }

                    }

                    NavigationHost(navController = navController)
                }
            }
        }
    }
}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LoginTheme {
        Greeting("Android")
    }
}