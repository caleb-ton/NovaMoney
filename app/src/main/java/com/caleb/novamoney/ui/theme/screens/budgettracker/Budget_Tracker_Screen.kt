package com.caleb.novamoney.ui.theme.screens.budgettracker


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.caleb.novamoney.R
import com.caleb.novamoney.ui.theme.NovaMoneyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NovaMoneyTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    BudgetTrackerApp()
                }
            }
        }
    }
}

@Composable
fun BudgetTrackerApp() {
    var selectedTab by remember { mutableStateOf("Dashboard") }

    Scaffold(
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Home, contentDescription = "Dashboard") },
                    label = { Text("Dashboard") },
                    selected = selectedTab == "Dashboard",
                    onClick = { selectedTab = "Dashboard" }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Star, contentDescription = "Rewards") },
                    label = { Text("Rewards") },
                    selected = selectedTab == "Rewards",
                    onClick = { selectedTab = "Rewards" }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Person, contentDescription = "Profile") },
                    label = { Text("Profile") },
                    selected = selectedTab == "Profile",
                    onClick = { selectedTab = "Profile" }
                )
            }
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            when (selectedTab) {
                "Dashboard" -> DashboardScreen()
                "Rewards" -> RewardsScreen()
                "Profile" -> ProfileScreen()
            }
        }
    }
}

@Composable
fun DashboardScreen() {
    var progress by remember { mutableStateOf(0.6f) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Monthly Budget",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(20.dp))

        AnimatedProgressBar(progress = progress)

        Spacer(modifier = Modifier.height(30.dp))

        Button(
            onClick = {
                if (progress < 1f) progress += 0.1f
            },
            shape = RoundedCornerShape(20.dp)
        ) {
            Text("Add Progress")
        }

        Spacer(modifier = Modifier.height(30.dp))

        AnimatedVisibility(
            visible = progress >= 1f,
            enter = fadeIn(animationSpec = tween(700)) + slideInVertically(),
            exit = fadeOut()
        ) {
            RewardBadge()
        }

        Spacer(modifier = Modifier.height(30.dp))

        CategoryList()
    }
}

@Composable
fun AnimatedProgressBar(progress: Float) {
    LinearProgressIndicator(
        progress = progress,
        modifier = Modifier
            .fillMaxWidth()
            .height(20.dp)
            .clip(RoundedCornerShape(10.dp)),
        color = MaterialTheme.colorScheme.primary,
        trackColor = MaterialTheme.colorScheme.primaryContainer
    )
}

@Composable
fun RewardBadge() {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFFFD700)),
        modifier = Modifier
            .padding(16.dp)
            .clickable { /* celebrate! */ }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(16.dp)
        ) {
            Icon(
                Icons.Default.Star,
                contentDescription = "Star",
                tint = Color.White,
                modifier = Modifier.size(40.dp)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                "You've unlocked a reward!",
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
    }
}

@Composable
fun CategoryList() {
    Column {
        CategoryItem("Food", Icons.Default.)
        CategoryItem("Food", Icons.Default.Fastfood)
        CategoryItem("Transport", Icons.Default.DirectionsCar)
        CategoryItem("Entertainment", Icons.Default.Movie)
        CategoryItem("Shopping", Icons.Default.ShoppingCart)
    }
}


@Composable
fun CategoryItem(name: String, icon: ImageVector) {
    var expanded by remember { mutableStateOf(false) }

    Card(
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { expanded = !expanded },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(16.dp)
            ) {
                Icon(icon, contentDescription = name, modifier = Modifier.size(32.dp))
                Spacer(modifier = Modifier.width(12.dp))
                Text(name, fontSize = 18.sp, fontWeight = FontWeight.Medium)
                Spacer(modifier = Modifier.weight(1f))
                Icon(
                    imageVector = if (expanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
                    contentDescription = if (expanded) "Collapse" else "Expand"
                )
            }

            AnimatedVisibility(visible = expanded) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Budget: \$500")
                    Text("Spent: \$350")
                    LinearProgressIndicator(
                        progress = 0.7f,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun RewardsScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "Your Rewards",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(20.dp))

        RewardBadge()
        RewardBadge()
    }
}

@Composable
fun ProfileScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // NOTE: Ensure R.drawable.profile exists or replace with Icons.Default.Person if missing
        Image(
            painter = painterResource(id = R.drawable.profile),
            contentDescription = "Profile",
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape)
                .background(Color.Gray)
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text("John Doe", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
        Text("john.doe@example.com", style = MaterialTheme.typography.bodyMedium)
    }
}

@Preview(showBackground = true)
@Composable
fun DashboardScreenPreview() {
    NovaMoneyTheme {
        DashboardScreen()
    }
}

@Preview(showBackground = true)
@Composable
fun RewardsScreenPreview() {
    NovaMoneyTheme {
        RewardsScreen()
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    NovaMoneyTheme {
        ProfileScreen()
    }
}
