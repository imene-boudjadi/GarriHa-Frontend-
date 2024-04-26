package com.example.login

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.core.content.edit
import androidx.navigation.NavHostController
import com.example.parkir.views.router.Router

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AuthView(navController: NavHostController) {
    val context = LocalContext.current
    val userDatabase = UserDatabase.getDatabase(context)
    val userDao = userDatabase.getUserDao()
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        var email by remember {
            mutableStateOf("")
        }

        var password by remember {
            mutableStateOf("")
        }



        Text(text = "Authe Screen")
        TextField(
            value = email,
            onValueChange = {
                email = it
            },
        )
        TextField(
            value = password,
            onValueChange = {
                password = it
            },
        )
        Button(onClick = {

            val user = userDao.getUserByEmailAndPassword(email,password)
            if (user == null){
                isConnected = true
                val prefs =
                    context.getSharedPreferences(
                        "prefs", Context.MODE_PRIVATE
                    )

                prefs.edit {
                    putBoolean(
                        "isConnected", true
                    )

                    putString(
                        "userId",
                        email
                    )
                }
                navController.navigateUp()
                navController.navigate(Router.ReservationsScreen.route)
            } else {
                Toast.makeText(context, "Authentification Failed", Toast.LENGTH_SHORT).show()
            }
        }) {
            Text(text = "Login")
        }
    }
}