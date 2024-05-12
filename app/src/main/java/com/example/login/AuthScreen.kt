package com.example.login

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
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
import com.example.login.retrofit.Endpoint
import com.example.login.retrofit.SignUpResponse
import com.example.login.retrofit.UserData
import com.example.login.retrofit.loginData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

//var isConnected = true // Déclaration de la variable isConnected
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AuthView(navController: NavHostController) {
    val context = LocalContext.current

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        var email by remember {
            mutableStateOf("")
        }

        var motDePasse by remember {
            mutableStateOf("")
        }
        var responseLogin by remember { mutableStateOf<Response<SignUpResponse>?>(null) } // Added response variable
        var showDialogUser by remember { mutableStateOf(false) }
        var dialogUserMessage by remember { mutableStateOf("") }

        Text(text = "Authe Screen")
        TextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.padding(vertical = 8.dp)
        )
        TextField(
            value = motDePasse,
            onValueChange = { motDePasse = it },
            label = { Text("mot De Passe") },
            modifier = Modifier.padding(vertical = 8.dp)
        )
        Button(onClick = {

            // Validate input fields
            if (validateLoginFields(email, motDePasse)) {
                val loginData = loginData(
                    email = email,
                    motDePasse = motDePasse
                )
                CoroutineScope(Dispatchers.IO).launch {
                    val loginResponse = try {
                        Endpoint.createEndpoint().login(loginData)
                    } catch (e: Exception) {
                        null
                    }

                    // Update response state
                    responseLogin = loginResponse
                    showDialogUser = true

                }
            } else {
                Toast.makeText(
                    context,
                    "Please fill all fields",
                    Toast.LENGTH_SHORT
                ).show()
            }

            if (showDialogUser){
//                isConnected = true
//                val prefs =
//                    context.getSharedPreferences(
//                        "prefs", Context.MODE_PRIVATE
//                    )
//
//                prefs.edit {
//                    putBoolean(
//                        "isConnected", true
//                    )
//
//                    putString(
//                        "userId",
//                        email
//                    )
//                }
//                navController.navigateUp()
//                navController.navigate(Router.ReservationsScreen.route)
                Toast.makeText(context, "Authentification succed", Toast.LENGTH_SHORT).show()
                showDialogUser = false
            } else {
                Toast.makeText(context, "Authentification Failed", Toast.LENGTH_SHORT).show()

            }
        }) {
            Text(text = "Login")
        }
    }
}
//@Composable
//fun AuthView(navController: NavHostController) {
//    val context = LocalContext.current
//
//    var email by remember { mutableStateOf("") }
//    var motDePasse by remember { mutableStateOf("") }
//    var responseLogin by remember { mutableStateOf<Response<SignUpResponse>?>(null) }
//    var showDialogUser by remember { mutableStateOf(false) }
//    var dialogUserMessage by remember { mutableStateOf("") }
//
//    Column(
//        modifier = Modifier.fillMaxSize(),
//        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.Center
//    ) {
//        Text(text = "Authentication Screen")
//
//        TextField(
//            value = email,
//            onValueChange = { email = it },
//            label = { Text("Email") },
//            modifier = Modifier.padding(vertical = 8.dp)
//        )
//        TextField(
//            value = motDePasse,
//            onValueChange = { motDePasse = it },
//            label = { Text("Password") },
//            modifier = Modifier.padding(vertical = 8.dp)
//        )
//
//        Button(onClick = {
//            // Validate input fields
//            if (validateLoginFields(email, motDePasse)) {
//                val loginData = loginData(email = email, motDePasse = motDePasse)
//                CoroutineScope(Dispatchers.IO).launch {
//                    val loginResponse = try {
//                        Endpoint.createEndpoint().login(loginData)
//                    } catch (e: Exception) {
//                        null
//                    }
//
//                    // Update response state
//                    responseLogin = loginResponse
//                    showDialogUser = true
//                }
//            } else {
//                showDialogUser = true // Show dialog for incomplete fields
//            }
//        }) {
//            Text(text = "Login")
//        }
//    }
//
//    // Display AlertDialog based on the showDialogUser state
//    if (showDialogUser) {
//        AlertDialog(
//            onDismissRequest = { showDialogUser = false }, // Dismiss the dialog
//            title = { Text(text = "Authentication Status") },
//            text = {
//                Text(
//                    text = responseLogin?.message() ?: "Please fill all fields"
//                )
//            },
//            confirmButton = {
//                Button(onClick = { showDialogUser = false }) {
//                    Text("OK")
//                }
//            }
//        )
//    }
//
//    // Navigate to the next screen if authentication is successful
//    if (responseLogin?.isSuccessful == true) {
//        navController.navigate(Router.ReservationsScreen.route)
//    }
//}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpView(navController: NavHostController) {
    val context = LocalContext.current

    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var NUmeroTel by remember { mutableStateOf("") }
    var PhotoUser by remember { mutableStateOf("") }
    var motDePasse by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var response by remember { mutableStateOf<Response<SignUpResponse>?>(null) } // Added response variable
    var showDialog by remember { mutableStateOf(false) }
    var dialogMessage by remember { mutableStateOf("") }
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
            value = NUmeroTel,
            onValueChange = { NUmeroTel = it },
            label = { Text("Numero De Telephone") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            modifier = Modifier.padding(vertical = 8.dp)
        )
        TextField(
            value = PhotoUser,
            onValueChange = { PhotoUser = it },
            label = { Text("Photo") },
            modifier = Modifier.padding(vertical = 8.dp)
        )
        TextField(
            value = motDePasse,
            onValueChange = { motDePasse = it },
            label = { Text("Password") },
//            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.padding(vertical = 8.dp)
        )
        TextField(
            value = confirmPassword,
            onValueChange = { confirmPassword = it },
            label = { Text("Confirm Password") },
            //    visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.padding(vertical = 8.dp)
        )

        Button(
            onClick = {
                // Validate input fields
                if (validateFields(
                        firstName,
                        lastName,
                        email,
                        NUmeroTel,
                        PhotoUser,
                        motDePasse,
                        confirmPassword
                    )
                ) {
                    // If fields are valid, proceed with sign-up
                    val userData = UserData(
                        userId = 0,
                        firstName = firstName,
                        lastName = lastName,
                        email = email,
                        NUmeroTel = NUmeroTel,
                        motDePasse = motDePasse,
                        PhotoUser = PhotoUser
                    )

                    // Call the sign-up endpoint
                    CoroutineScope(Dispatchers.IO).launch {
                        val signUpResponse = try {
                            Endpoint.createEndpoint().signUp(userData)
                        } catch (e: Exception) {
                            null
                        }

                        // Update response state
                        response = signUpResponse
                        showDialog = true // Corrected the syntax

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
        ) {
            Text(text = "Sign Up")
        }

        if (showDialog) {
            handleSignUpResponse(response, navController, context)
        }

    }
}


private fun validateFields(firstName: String, lastName: String, email: String, NUmeroTel: String, PhotoUser: String, password: String, confirmPassword: String): Boolean {
    // Basic validation - ensure all fields are filled and passwords match
    return firstName.isNotBlank() && lastName.isNotBlank() && email.isNotBlank() && NUmeroTel.isNotBlank() && PhotoUser.isNotBlank() && password.isNotBlank() && password == confirmPassword
}

private fun validateLoginFields(email: String, password: String) : Boolean {
    // Basic validation - ensure all fields are filled and passwords match
    return email.isNotBlank() && password.isNotBlank()
}

//@Composable
//private fun handleSignUpResponse(response: Response<SignUpResponse>?, navController: NavHostController, context: Context) {
//    val message = if (response != null && response.isSuccessful) {
//        val signUpResponse = response.body()
//        if (signUpResponse != null && signUpResponse.status == 200) {
//          // Sign-up successful, navigate to next screen
//            navController.navigateUp()
//            "Account created successfully"
//        } else {
////             Sign-up failed, handle error
//            "Sign-up failed: ${signUpResponse?.message}"
//        }
//    } else {
////         Network error or other issues, handle error
//        "Sign-up failed: Network error"
//    }
//
//    AlertDialog(
//        onDismissRequest = { /* Dismiss the dialog */ },
//        title = { Text(text = "Sign-up Status") },
//        text = { Text(text = message) },
//        confirmButton = {
//            Button(onClick = { /* Dismiss the dialog */ }) {
//                Text("OK")
//            }
//        }
//    )
//}

@Composable
private fun handleSignUpResponse(response: Response<SignUpResponse>?, navController: NavHostController, context: Context) {


    val message = when {
        response != null && response.isSuccessful -> {
            val signUpResponse = response.body()
            when {
                signUpResponse != null && signUpResponse.data != null -> {
                    // Inscription réussie
                    "Compte créé avec succès"
                }
                signUpResponse?.message == "Adresse mail deja utilise" -> {
                    // Email déjà utilisé, afficher un message approprié
                    "Adresse e-mail déjà utilisée"
                }
                else -> {
                    // Échec de l'inscription, gérer l'erreur
                    "Échec de l'inscription: ${signUpResponse?.status}"
                }
            }
        }
        else -> {
            // Erreur réseau ou autres problèmes, gérer l'erreur
            "Échec de l'inscription: Erreur réseau"
        }
    }

    AlertDialog(
        onDismissRequest = { },
        title = { Text(text = "Statut de l'inscription") },
        text = { Text(text = message) },
        confirmButton = {
            Button(onClick = {}) {
                Text("OK")
            }
        }
    )
}


