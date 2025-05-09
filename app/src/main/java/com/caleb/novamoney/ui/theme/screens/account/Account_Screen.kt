package com.caleb.novamoney.ui.theme.screens.account



import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.caleb.novamoney.R
import com.caleb.novamoney.navigation.*

@Composable
fun AccountScreenWithBottomBar(navController: NavController) {

    var selectedBottomItem by remember { mutableStateOf("Account") }

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
        // Main Content
        AccountScreenContent(navController, Modifier.padding(innerPadding))
    }
}

@Composable
fun AccountScreenContent(navController: NavController, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Profile Section
        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(4.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.profile),
                    contentDescription = "Profile Picture",
                    modifier = Modifier
                        .size(80.dp)
                        .clip(CircleShape)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text("John Doe", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                Text("john.doe@example.com", fontSize = 14.sp)
                Text("@johndoe", fontSize = 14.sp)
                Text(
                    "Account ID: 123456789",
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Spacer(modifier = Modifier.height(8.dp))
                Button(onClick = { /* Edit Profile */ }) {
                    Text("Edit Profile")
                }
            }
        }

        SettingsCard(title = "Account Settings", options = listOf(
            "Change Password",
            "Manage Payment Methods",
            "Subscription & Plan Info",
            "Linked Accounts"
        ))

        SettingsCard(title = "Preferences", options = listOf(
            "Language & Region",
            "Notification Preferences",
            "Privacy Settings",
            "Theme",
            "Data & Personalization"
        ))

        SettingsCard(title = "Security", options = listOf(
            "Two-Factor Authentication",
            "Login Activity / Devices",
            "Deactivate / Delete Account"
        ))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(onClick = { navController.navigate(ROUTE_INVESTMENT) }, modifier = Modifier.weight(1f)) {
                Text("Investment")
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = { navController.navigate(ROUTE_BUDGET_TRACKER) }, modifier = Modifier.weight(1f)) {
                Text("Budget Tracker")
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(onClick = { navController.navigate(ROUTE_TRANSACTION) }, modifier = Modifier.weight(1f)) {
                Text("Transaction")
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = { navController.navigate(ROUTE_PROFILE) }, modifier = Modifier.weight(1f)) {
                Text("Profile")
            }
        }
    }
}

@Composable
fun SettingsCard(title: String, options: List<String>) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(title, fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
            Spacer(modifier = Modifier.height(8.dp))
            options.forEach { option ->
                Button(
                    onClick = { /* Handle click */ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    colors = ButtonDefaults.outlinedButtonColors()
                ) {
                    Text(option)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AccountScreenWithBottomBarPreview() {
    AccountScreenWithBottomBar(rememberNavController())
}
