package com.example.login

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.core.content.edit
import androidx.navigation.NavHostController
import com.example.parkir.views.router.Router

//var isConnected = true // Déclaration de la variable isConnected
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
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpView(navController: NavHostController) {
    val context = LocalContext.current
    val userDatabase = UserDatabase.getDatabase(context)
    val userDao = userDatabase.getUserDao()

    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Create Account", style = MaterialTheme.typography.bodyMedium)

        TextField(
            value = firstName,
            onValueChange = { firstName = it },
            label = { Text("First Name") },
            modifier = Modifier.padding(vertical = 8.dp)
        )
        TextField(
            value = lastName,
            onValueChange = { lastName = it },
            label = { Text("Last Name") },
            modifier = Modifier.padding(vertical = 8.dp)
        )
        TextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.padding(vertical = 8.dp)
        )
        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.padding(vertical = 8.dp)
        )
        TextField(
            value = confirmPassword,
            onValueChange = { confirmPassword = it },
            label = { Text("Confirm Password") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.padding(vertical = 8.dp)
        )

        Button(
            onClick = {
                // Votre logique de création de compte ici
                // Vous pouvez utiliser les données firstName, lastName, email, password pour créer le compte
                // Assurez-vous de valider les données et de les envoyer au serveur si elles sont valides
                // Puis naviguez vers l'écran suivant si la création de compte réussit
            },
            modifier = Modifier.padding(vertical = 16.dp)
        ) {
            Text(text = "Create Account")
        }
    }
}
