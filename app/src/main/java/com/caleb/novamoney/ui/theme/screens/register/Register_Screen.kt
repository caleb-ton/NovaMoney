package com.caleb.novamoney.ui.theme.screens.register

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
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
import androidx.compose.ui.text.input.VisualTransformation
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.caleb.novamoney.navigation.ROUTE_HOME
import com.caleb.novamoney.navigation.ROUTE_LOGIN

@Composable
fun SignUpScreen(navController: NavController) {
    // State variables for input fields
    var username by remember { mutableStateOf(TextFieldValue("")) }
    var name by remember { mutableStateOf(TextFieldValue("")) }
    var email by remember { mutableStateOf(TextFieldValue("")) }
    var password by remember { mutableStateOf(TextFieldValue("")) }
    var confirmPassword by remember { mutableStateOf(TextFieldValue("")) }
    var isLoginEnabled by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }

    // Validation messages
    var usernameError by remember { mutableStateOf("") }
    var nameError by remember { mutableStateOf("") }
    var emailError by remember { mutableStateOf("") }
    var passwordError by remember { mutableStateOf("") }
    var confirmPasswordError by remember { mutableStateOf("") }
    var confirmPasswordVisible by remember { mutableStateOf(false) }
    var isSignUpEnabled by remember { mutableStateOf(false) }

    // Check if all fields are filled
    LaunchedEffect(username.text, name.text, email.text, password.text, confirmPassword.text) {
        isSignUpEnabled = username.text.isNotEmpty() && name.text.isNotEmpty() && email.text.isNotEmpty() &&
                password.text.isNotEmpty() && confirmPassword.text.isNotEmpty() &&
                password.text == confirmPassword.text
    }

    // Handle Sign-Up button click
    fun handleSignUp() {
        // Basic validation for the form fields
        usernameError = if (username.text.isEmpty()) "Username is required" else ""
        nameError = if (name.text.isEmpty()) "Name is required" else ""
        emailError = if (email.text.isEmpty()) "Email is required" else ""
        passwordError = if (password.text.isEmpty()) "Password is required" else ""
        confirmPasswordError = if (confirmPassword.text != password.text) "Passwords do not match" else ""

        if (usernameError.isEmpty() && nameError.isEmpty() && emailError.isEmpty() &&
            passwordError.isEmpty() && confirmPasswordError.isEmpty()
        ) {
            // Here you can handle saving user data or any backend call.
            navController.navigate(ROUTE_HOME)
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
        Text(
            "Create an Account",
            style = MaterialTheme.typography.headlineLarge,
            fontFamily = FontFamily.Cursive
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Username input field
        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text(text = "Username") },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.Person,
                    contentDescription = "Person Icon"
                )
            },
            modifier = Modifier.fillMaxWidth(),
            isError = usernameError.isNotEmpty(),
            singleLine = true
        )
        if (usernameError.isNotEmpty()) {
            Text(usernameError, color = Color.Red, style = MaterialTheme.typography.bodySmall)
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
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
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
            modifier = Modifier.fillMaxWidth(),
            isError = emailError.isNotEmpty(),
            singleLine = true
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
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password),
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

        Spacer(modifier = Modifier.height(16.dp))

        // Confirm Password input field
        OutlinedTextField(
            value = confirmPassword,
            onValueChange = { confirmPassword = it },
            label = { Text("Confirm Password") },
            visualTransformation = if (confirmPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password),
            keyboardActions = KeyboardActions(onDone = { handleSignUp() }),  // or any action you want here
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.Lock,
                    contentDescription = "Confirm Password Icon"
                )
            },
            trailingIcon = {
                IconButton(onClick = { confirmPasswordVisible = !confirmPasswordVisible }) {
                    Text(text = if (confirmPasswordVisible) "Hide" else "Show")
                }
            },
            modifier = Modifier.fillMaxWidth(),
            isError = confirmPasswordError.isNotEmpty(),
            singleLine = true
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
        TextButton(onClick = { navController.navigate(ROUTE_LOGIN) }) {
            Text("Already have an account? Log in")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSignUpScreen() {
    SignUpScreen(rememberNavController())
}
