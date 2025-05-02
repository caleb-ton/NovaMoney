package com.caleb.novamoney.ui.theme.screens.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

@Composable
fun LoginScreen(onLoginSuccess: () -> Unit) {
    // State variables for username and password
    var username by remember { mutableStateOf(TextFieldValue("")) }
    var password by remember { mutableStateOf(TextFieldValue("")) }
    var isLoginEnabled by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var email by remember { mutableStateOf(TextFieldValue("")) }


    // Enable the login button only if both fields are non-empty
    LaunchedEffect(username.text, password.text) {
        isLoginEnabled = username.text.isNotEmpty() && password.text.isNotEmpty()
    }

    // Handle login
    fun handleLogin() {
        if (username.text == "user" && password.text == "password") {
            onLoginSuccess() // Simulate login success
        } else {
            errorMessage = "Invalid username or password"
        }
    }

    // Login screen UI
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(Color.Transparent),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Login", style = MaterialTheme.typography.headlineLarge)

        Spacer(modifier = Modifier.height(32.dp))

        // Username TextField
        OutlinedTextField(value =email , onValueChange = {email=it},
            label = { Text(text = "Enter Email") },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.Email,
                    contentDescription = "Email Icon"
                )
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Next
            ),
            modifier = Modifier
                .fillMaxWidth()



            )
        Spacer(modifier = Modifier.height(32.dp))


        Spacer(modifier = Modifier.height(16.dp))

        // Password TextField
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password),
            keyboardActions = KeyboardActions(onDone = { handleLogin() }),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.Lock,
                    contentDescription = "Password Icon"
                )},
            trailingIcon = {
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Text(text = if (passwordVisible) "Hide" else "Show")
                }
            },
            modifier = Modifier.fillMaxWidth(),
            isError = errorMessage.isNotEmpty(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Error message
        if (errorMessage.isNotEmpty()) {
            Text(errorMessage, color = Color.Red, style = MaterialTheme.typography.bodySmall)
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Login Button
        Button(
            onClick = {    },
            modifier = Modifier
                .fillMaxWidth() // Ensure the button takes the full width of the parent
                .padding(horizontal = 35.dp), // Optional: Remove horizontal padding if needed
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Red
            )
        ) {
            Text(
                text = "Log In",
                color = Color.Cyan,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 20.sp
            )
        }


        Spacer(modifier = Modifier.height(16.dp))

        // Optional: You can add a sign-up button here if you need one
        Button(
            onClick = {       },
            modifier = Modifier.fillMaxWidth().width(30.dp)
                .padding(horizontal = 35.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Red

            )
        ) {
            Text(text = "Don't have an account? Sign Up",
                color = Color.Cyan,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 16.sp
            )
        }

    }
}

@Preview(showBackground = true)
@Composable
fun PreviewLoginScreen() {
    LoginScreen(onLoginSuccess = {
        // Handle login success (for example, navigate to the home screen)
    })
}