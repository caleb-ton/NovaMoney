package com.caleb.novamoney.ui.theme.screens.profile



import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberImagePainter
import com.caleb.novamoney.R
import com.caleb.novamoney.navigation.ROUTE_ACCOUNT
import com.caleb.novamoney.navigation.ROUTE_HOME
import com.caleb.novamoney.navigation.ROUTE_NOTIFICATION
import com.caleb.novamoney.navigation.ROUTE_PROFILE
import com.caleb.novamoney.model.ProfileViewModel

@Composable
fun EditProfileScreenWithBottomBar(navController: NavController) {
    var selectedBottomItem by remember { mutableStateOf("Profile") }
    val profileViewModel: ProfileViewModel = viewModel()

    // Register the image picker launcher
    val getContent = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        profileViewModel.updateProfileImage(uri)
    }

    Scaffold(
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
                    label = { Text("Home") },
                    selected = selectedBottomItem == "Home",
                    onClick = {
                        navController.navigate(ROUTE_HOME)
                        selectedBottomItem = "Home"
                    }
                )

                NavigationBarItem(
                    icon = { Icon(Icons.Default.Person, contentDescription = "Profile") },
                    label = { Text("Profile") },
                    selected = selectedBottomItem == "Profile",
                    onClick = {
                        navController.navigate(ROUTE_PROFILE)
                        selectedBottomItem = "Profile"
                    }
                )

                NavigationBarItem(
                    icon = { Icon(Icons.Default.Notifications, contentDescription = "Alerts") },
                    label = { Text("Alerts") },
                    selected = selectedBottomItem == "Alerts",
                    onClick = {
                        navController.navigate(ROUTE_NOTIFICATION)
                        selectedBottomItem = "Alerts"
                    }
                )

                NavigationBarItem(
                    icon = { Icon(Icons.Default.AccountCircle, contentDescription = "Account") },
                    label = { Text("Account") },
                    selected = selectedBottomItem == "Account",
                    onClick = {
                        navController.navigate(ROUTE_ACCOUNT)
                        selectedBottomItem = "Account"
                    }
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
                IconButton(onClick = {
                    navController.navigate(ROUTE_PROFILE)
                }) {
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
                        profileViewModel.saveProfile()
                    }
                ) {
                    Icon(Icons.Default.Check, contentDescription = "Save")
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
                    painter = if (profileViewModel.profileImageUri.value != null) {
                        rememberImagePainter(profileViewModel.profileImageUri.value)
                    } else {
                        painterResource(id = R.drawable.profile) // Default profile picture
                    },
                    contentDescription = "Profile Picture",
                    modifier = Modifier
                        .size(100.dp)
                        .clip(CircleShape)
                        .clickable {
                            // Open gallery when clicked
                            getContent.launch("image/*")
                        }
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Name input
            OutlinedTextField(
                value = profileViewModel.name.value,
                onValueChange = { profileViewModel.updateName(it) }, // Directly passing `it` (the text string) to updateName
                label = { Text("Name") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )


            Spacer(modifier = Modifier.height(16.dp))

            // Email input
            OutlinedTextField(
                value = profileViewModel.email.value,
                onValueChange = { profileViewModel.updateName(it) },
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
    EditProfileScreenWithBottomBar(rememberNavController())
}

