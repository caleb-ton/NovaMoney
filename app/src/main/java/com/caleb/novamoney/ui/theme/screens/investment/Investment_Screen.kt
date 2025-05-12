package com.caleb.novamoney.ui.theme.screens.investment


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import com.caleb.novamoney.ui.theme.NovaMoneyTheme

@Composable
fun InvestmentScreenWithBottomBar(navController: NavController) {
    var selectedBottomItem by remember { mutableStateOf("Home") }

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
        InvestmentScreen(modifier = Modifier.padding(innerPadding))
    }
}

@Composable
fun InvestmentScreen(modifier: Modifier = Modifier) {
    val totalInvestment = 10_000f
    val currentValue = 12_500f
    val gainLoss = currentValue - totalInvestment
    val percentageChange = (gainLoss / totalInvestment) * 100

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Logo Box at top
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.applogo),
                contentDescription = "App Logo",
                modifier = Modifier.fillMaxSize()
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = "Investment Summary",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(20.dp))

        Card(
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                SummaryRow(label = "Total Investment", value = "$${String.format("%.2f", totalInvestment)}")
                SummaryRow(label = "Current Value", value = "$${String.format("%.2f", currentValue)}")
                SummaryRow(
                    label = "Gain/Loss",
                    value = "$${String.format("%.2f", gainLoss)}",
                    valueColor = if (gainLoss >= 0) Color.Green else Color.Red
                )
                SummaryRow(
                    label = "Percentage Change",
                    value = "${String.format("%.2f", percentageChange)}%",
                    valueColor = if (percentageChange >= 0) Color.Green else Color.Red
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Performance Overview",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.SemiBold
        )

        Spacer(modifier = Modifier.height(16.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .background(MaterialTheme.colorScheme.surfaceVariant, RoundedCornerShape(12.dp)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Graph Placeholder",
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
fun SummaryRow(label: String, value: String, valueColor: Color = MaterialTheme.colorScheme.onSurface) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = label, fontSize = 16.sp)
        Text(
            text = value,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            color = valueColor
        )
    }
}

@Preview(showBackground = true)
@Composable
fun InvestmentScreenWithBottomBarPreview() {
    NovaMoneyTheme {
        InvestmentScreenWithBottomBar(rememberNavController())
    }
}
