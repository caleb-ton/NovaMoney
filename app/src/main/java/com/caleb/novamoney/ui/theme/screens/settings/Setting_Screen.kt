package com.caleb.novamoney.ui.theme.screens.settings


import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.caleb.novamoney.navigation.ROUTE_ACCOUNT
import com.caleb.novamoney.navigation.ROUTE_NOTIFICATION

@Composable
fun SettingsScreen(navController: NavController) {
    val context = LocalContext.current

    Scaffold(
        topBar = {
            Text(text = "Settings")
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text("General", fontWeight = FontWeight.Bold, fontSize = 14.sp, color = Color.Gray)
            Spacer(Modifier.height(8.dp))
            SettingsItem(
                icon = Icons.Default.Person,
                title = "Account",
                subtitle = "Manage your account",
                onClick = {
                    navController.navigate(ROUTE_ACCOUNT)
                    Toast.makeText(context, "Account clicked", Toast.LENGTH_SHORT).show()
                }
            )
            SettingsItem(
                icon = Icons.Default.Notifications,
                title = "Notifications",
                subtitle = "Notification preferences",
                onClick = {
                    navController.navigate(ROUTE_NOTIFICATION)
                    Toast.makeText(context, "Notifications clicked", Toast.LENGTH_SHORT).show()
                }
            )
            Spacer(Modifier.height(16.dp))
//            Text("Support", fontWeight = FontWeight.Bold, fontSize = 14.sp, color = Color.Gray)
//            Spacer(Modifier.height(8.dp))
//            SettingsItem(
//                icon = Icons.Default.Build,
//                title = "Help & Support",
//                subtitle = "Get assistance",
//                onClick = {
//                    Toast.makeText(context, "Help clicked", Toast.LENGTH_SHORT).show()
//                }
//            )
//            SettingsItem(
//                icon = Icons.Default.Info,
//                title = "About",
//                subtitle = "App information",
//                onClick = {
//                    Toast.makeText(context, "About clicked", Toast.LENGTH_SHORT).show()
//                }
//            )
            Spacer(Modifier.weight(1f))
            Button(
                onClick = {
                    Toast.makeText(context, "Logged out", Toast.LENGTH_SHORT).show()
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
            ) {
                Icon(Icons.Default.ExitToApp, contentDescription = "Logout")
                Spacer(Modifier.width(8.dp))
                Text("Logout")
            }
        }
    }
}

@Composable
fun SettingsItem(
    icon: ImageVector,
    title: String,
    subtitle: String,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(icon, contentDescription = title, modifier = Modifier.size(24.dp))
        Spacer(Modifier.width(16.dp))
        Column {
            Text(title, fontSize = 16.sp, fontWeight = FontWeight.Medium)
            Text(subtitle, fontSize = 12.sp, color = Color.Gray)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSettingsScreen() {
    MaterialTheme {
        SettingsScreen(rememberNavController())
    }
}
