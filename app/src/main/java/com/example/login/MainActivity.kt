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
////        val userDatabase = UserDatabase.getDatabase(applicationContext)
//        setContent {
//            val context = LocalContext.current
//
//
////
//            val prefs =
//                context.getSharedPreferences(
//                    "prefs", Context.MODE_PRIVATE
//                )
//            isConnected = prefs.getBoolean(
//                "isConnected", false
//            )
//
//            LoginTheme {
//                // A surface container using the 'background' color from the theme
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colorScheme.background
//                ) {
//                    val navController = rememberNavController()
//
//                    lifecycleScope.launch {
//                        withContext(Dispatchers.IO){
//                            val userDao = UserDatabase.getDatabase(context).getUserDao()
//
//                            // Dummy user data
//                            val user1 = User(firstName = "imene", lastName = "Boudjadi", email = "ki_boudjadi@esi.dz", motDePasse = "test")
//                            val user2 = User(firstName = "yasmine", lastName = "Ghouar", email = "ky_ghouar@esi.dz", motDePasse = "test")
//
//                            // Insert users into the database
//                            userDao.addUser(user1)
//                            userDao.addUser(user2)
//                        }
//                    }
//                    NavigationHost(navController = navController)
//                }
//            }
//        }
//    }
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