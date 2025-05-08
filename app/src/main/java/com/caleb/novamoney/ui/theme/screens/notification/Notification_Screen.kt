package com.caleb.novamoney.ui.theme.screens.notification


import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.caleb.novamoney.navigation.ROUTE_ACCOUNT
import com.caleb.novamoney.navigation.ROUTE_HOME
import com.caleb.novamoney.navigation.ROUTE_NOTIFICATION
import com.caleb.novamoney.navigation.ROUTE_PROFILE

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NotificationScreenWithBottomBar(navController: NavController) {
    var selectedBottomItem by remember { mutableStateOf("Alerts") }

    val notifications = listOf(
        Notification(1, "New Message Received", "You have a new message from John Doe.", "info", "2 minutes ago"),
        Notification(2, "Payment Successful", "Your payment of $50 has been processed.", "success", "1 hour ago"),
        Notification(3, "Server Downtime", "The server will be down for maintenance at midnight.", "warning", "3 hours ago")
    )

    Scaffold(
        topBar = {
            Text(
                text = "Notifications",
                modifier = Modifier.padding(16.dp),
                style = MaterialTheme.typography.titleLarge
            )
        },
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
        },
        content = {
            Column(modifier = Modifier.padding(16.dp)) {
                LazyColumn {
                    items(notifications) { notification ->
                        NotificationCard(notification)
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }
//                Button(
//                    onClick = { /* Handle mark all as read */ },
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(top = 16.dp)
//                ) {
//                    Text("Mark all as read")
//                }
            }
        }
    )
}

@Composable
fun NotificationCard(notification: Notification) {
    val icon = when (notification.type) {
        "info" -> Icons.Default.Notifications
        "success" -> Icons.Default.CheckCircle
        "warning" -> Icons.Default.Warning
        else -> Icons.Default.Notifications
    }
    val iconColor = when (notification.type) {
        "info" -> Color.Blue
        "success" -> Color.Green
        "warning" -> Color.Yellow
        else -> Color.Gray
    }

    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(icon, contentDescription = null, tint = iconColor, modifier = Modifier.size(40.dp))
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(text = notification.title, style = MaterialTheme.typography.titleMedium)
                Text(text = notification.description, style = MaterialTheme.typography.bodyMedium, color = Color.Gray)
                Text(text = notification.time, style = MaterialTheme.typography.bodySmall, color = Color.LightGray)
            }
        }
    }
}

data class Notification(
    val id: Int,
    val title: String,
    val description: String,
    val type: String,
    val time: String
)

@Preview(showBackground = true)
@Composable
fun NotificationScreenWithBottomBarPreview() {
    NotificationScreenWithBottomBar(rememberNavController())
}
