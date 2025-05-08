package com.caleb.novamoney.ui.theme.screens.profile



import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.caleb.novamoney.R
import com.caleb.novamoney.navigation.ROUTE_ACCOUNT
import com.caleb.novamoney.navigation.ROUTE_HOME
import com.caleb.novamoney.navigation.ROUTE_NOTIFICATION
import com.caleb.novamoney.navigation.ROUTE_PROFILE

@Composable
fun ProfileScreenWithBottomBar(navController: NavController) {
    var selectedBottomItem by remember { mutableStateOf("Profile") }

    Scaffold(
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
                    label = { Text("Home") },
                    selected = selectedBottomItem == "Home",
                    onClick = {
                        navController.navigate(ROUTE_HOME)
                        selectedBottomItem = "Home" }
                )

//                NavigationBarItem(
//                    icon = {
//                        Icon(
//                            painter = painterResource(id = R.drawable.aaron),
//                            contentDescription = "AI",
//                            modifier = Modifier.size(24.dp),
//                            tint = Color.Unspecified
//                        )
//                    },
//                    label = { Text("AI") },
//                    selected = selectedBottomItem == "AI",
//                    onClick = { selectedBottomItem = "AI" }
//                )

                NavigationBarItem(
                    icon = { Icon(Icons.Default.Person, contentDescription = "Profile") },
                    label = { Text("Profile") },
                    selected = selectedBottomItem == "Profile",
                    onClick = {
                        navController.navigate(ROUTE_PROFILE)
                        selectedBottomItem = "Profile" }
                )

                NavigationBarItem(
                    icon = { Icon(Icons.Default.Notifications, contentDescription = "Alerts") },
                    label = { Text("Alerts") },
                    selected = selectedBottomItem == "Alerts",
                    onClick = {
                        navController.navigate(ROUTE_NOTIFICATION)
                        selectedBottomItem = "Alerts" }
                )

                NavigationBarItem(
                    icon = { Icon(Icons.Default.AccountCircle, contentDescription = "Account") },
                    label = { Text("Account") },
                    selected = selectedBottomItem == "Account",
                    onClick = {
                        navController.navigate(ROUTE_ACCOUNT)
                        selectedBottomItem = "Account" }
                )
            }
        }
    ) { innerPadding ->
        ProfileScreen(Modifier.padding(innerPadding))
    }
}

@Composable
fun ProfileScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Top bar with back button
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
//            IconButton(onClick = { /* TODO: handle back action */ })
//            {
//                Icon(Icons.Default.ArrowBack, contentDescription = "Back")
//            }
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Profile",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Profile picture and user info
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = R.drawable.profile), // replace with your drawable
                contentDescription = "Profile Picture",
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(
                    text = "John Doe",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "johndoe@example.com",
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                )
            }
            Spacer(modifier = Modifier.weight(1f))
//            IconButton(onClick = {/* TODO: handle save action */ }) {
//                Icon(Icons.Default.Check, contentDescription = "Save Changes")
//            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Profile options list
        ProfileOptionItem(
            icon = Icons.Default.Settings,
            title = "Settings",
            onClick = { /* TODO: handle settings click */ }
        )

        Spacer(modifier = Modifier.height(16.dp)) // smaller space between

        ProfileOptionItem(
            icon = Icons.Default.Notifications,
            title = "Notifications",
            onClick = {
            /* TODO: handle notifications click */ }
        )

        Spacer(modifier = Modifier.height(16.dp)) // smaller space between

        ProfileOptionItem(
            icon = Icons.Default.ExitToApp,
            title = "Logout",
            onClick = { /* TODO: handle logout click */ }
        )
    }
}

@Composable
fun ProfileOptionItem(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    title: String,
    onClick: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(16.dp)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = title,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = title,
                fontSize = 16.sp
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    ProfileScreenWithBottomBar(rememberNavController())
}
