package com.caleb.novamoney.ui.theme.screens.profile


import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.caleb.novamoney.R

@Composable
fun EditProfileScreenWithBottomBar(
    onBackClick: () -> Unit = {},
    onSaveClick: (String, String) -> Unit = { _, _ -> }
) {
    var selectedBottomItem by remember { mutableStateOf("Profile") }
    var name by remember { mutableStateOf(TextFieldValue("John Doe")) }
    var email by remember { mutableStateOf(TextFieldValue("johndoe@example.com")) }
    var isSaving by remember { mutableStateOf(false) }

    Scaffold(
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
                    label = { Text("Home") },
                    selected = selectedBottomItem == "Home",
                    onClick = { selectedBottomItem = "Home" }
                )

                NavigationBarItem(
                    icon = {
                        Icon(
                            painter = painterResource(id = R.drawable.aaron),
                            contentDescription = "AI",
                            modifier = Modifier.size(24.dp),
                            tint = Color.Unspecified
                        )
                    },
                    label = { Text("AI") },
                    selected = selectedBottomItem == "AI",
                    onClick = { selectedBottomItem = "AI" }
                )

                NavigationBarItem(
                    icon = { Icon(Icons.Default.Person, contentDescription = "Profile") },
                    label = { Text("Profile") },
                    selected = selectedBottomItem == "Profile",
                    onClick = { selectedBottomItem = "Profile" }
                )

                NavigationBarItem(
                    icon = { Icon(Icons.Default.Notifications, contentDescription = "Alerts") },
                    label = { Text("Alerts") },
                    selected = selectedBottomItem == "Alerts",
                    onClick = { selectedBottomItem = "Alerts" }
                )

                NavigationBarItem(
                    icon = { Icon(Icons.Default.AccountCircle, contentDescription = "Account") },
                    label = { Text("Account") },
                    selected = selectedBottomItem == "Account",
                    onClick = { selectedBottomItem = "Account" }
                )
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            // Top bar with back and save
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                IconButton(onClick = { onBackClick() }) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                }
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = "Edit Profile",
                    style = MaterialTheme.typography.titleLarge
                )
                Spacer(modifier = Modifier.weight(1f))
                IconButton(
                    onClick = {
                        if (!isSaving) {
                            isSaving = true
                            onSaveClick(name.text, email.text)
                            // Here you could launch a coroutine or similar to handle async save
                            isSaving = false
                        }
                    },
                    enabled = !isSaving
                ) {
                    Icon(
                        Icons.Default.Check,
                        contentDescription = if (isSaving) "Saving..." else "Save"
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Profile picture
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .align(Alignment.CenterHorizontally)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.profile),
                    contentDescription = "Profile Picture",
                    modifier = Modifier
                        .size(100.dp)
                        .clip(CircleShape)
                        .clickable { /* TODO: handle change picture */ }
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Name input
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Name") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Email input
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EditProfileScreenWithBottomBarPreview() {
    EditProfileScreenWithBottomBar()
}
