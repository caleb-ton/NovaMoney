package com.caleb.novamoney.ui.theme.screens.register


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction

@Composable
fun SignUpScreen(onSignUpSuccess: () -> Unit, onNavigateToLogin: () -> Unit) {
    // State variables for input fields
    var name by remember { mutableStateOf(TextFieldValue("")) }
    var email by remember { mutableStateOf(TextFieldValue("")) }
    var password by remember { mutableStateOf(TextFieldValue("")) }
    var confirmPassword by remember { mutableStateOf(TextFieldValue("")) }

    // Validation messages
    var nameError by remember { mutableStateOf("") }
    var emailError by remember { mutableStateOf("") }
    var passwordError by remember { mutableStateOf("") }
    var confirmPasswordError by remember { mutableStateOf("") }
    var username by remember { mutableStateOf(TextFieldValue("")) }

    var isSignUpEnabled by remember { mutableStateOf(false) }

    // Check if all fields are filled
    LaunchedEffect(name.text, email.text, password.text, confirmPassword.text) {
        isSignUpEnabled = name.text.isNotEmpty() && email.text.isNotEmpty() &&
                password.text.isNotEmpty() && confirmPassword.text.isNotEmpty() &&
                password.text == confirmPassword.text
    }

    // Handle Sign-Up button click
    fun handleSignUp() {
        // Basic validation for the form fields
        nameError = if (name.text.isEmpty()) "Name is required" else ""
        emailError = if (email.text.isEmpty()) "Email is required" else ""
        passwordError = if (password.text.isEmpty()) "Password is required" else ""
        confirmPasswordError = if (confirmPassword.text != password.text) "Passwords do not match" else ""

        if (nameError.isEmpty() && emailError.isEmpty() && passwordError.isEmpty() && confirmPasswordError.isEmpty()) {
            // Simulate successful sign-up (e.g., send data to backend)
            onSignUpSuccess()
        }
    }

    // Sign-up form UI
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Create an Account", style = MaterialTheme.typography.headlineLarge,
            fontFamily = FontFamily.Cursive)

        Spacer(modifier = Modifier.height(32.dp))

        // username input field
        OutlinedTextField(value = username, onValueChange = {username=it},
            label = { Text(text = "Username") },

            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.Person,
                    contentDescription = "Person  Icon"
                )},

            modifier = Modifier
                .fillMaxWidth()

        )
        if (nameError.isNotEmpty()) {
            Text(nameError, color = Color.Red, style = MaterialTheme.typography.bodySmall)
        }

        Spacer(modifier = Modifier.height(16.dp))
        // Name input field
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Full Name") },
            modifier = Modifier.fillMaxWidth(),
            isError = nameError.isNotEmpty(),
            singleLine = true
        )
        if (nameError.isNotEmpty()) {
            Text(nameError, color = Color.Red, style = MaterialTheme.typography.bodySmall)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Email input field
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
        if (emailError.isNotEmpty()) {
            Text(emailError, color = Color.Red, style = MaterialTheme.typography.bodySmall)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Password input field
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth(),
            isError = passwordError.isNotEmpty(),
            singleLine = true,
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password)
        )
        if (passwordError.isNotEmpty()) {
            Text(passwordError, color = Color.Red, style = MaterialTheme.typography.bodySmall)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Confirm Password input field
        OutlinedTextField(
            value = confirmPassword,
            onValueChange = { confirmPassword = it },
            label = { Text("Confirm Password") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth(),
            isError = confirmPasswordError.isNotEmpty(),
            singleLine = true,
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password)
        )
        if (confirmPasswordError.isNotEmpty()) {
            Text(confirmPasswordError, color = Color.Red, style = MaterialTheme.typography.bodySmall)
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Sign-Up Button
        Button(
            onClick = { handleSignUp() },
            enabled = isSignUpEnabled,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text("Sign Up")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Navigate to Login screen (if the user already has an account)
        TextButton(onClick = onNavigateToLogin) {
            Text("Already have an account? Log in")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSignUpScreen() {
    SignUpScreen(onSignUpSuccess = {
        // Handle successful sign-up (e.g., navigate to the home screen)
    }, onNavigateToLogin = {
        // Navigate to the login screen
    })
}