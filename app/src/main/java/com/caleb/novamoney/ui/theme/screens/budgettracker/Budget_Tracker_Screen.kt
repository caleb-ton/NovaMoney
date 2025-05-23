package com.caleb.novamoney.ui.theme.screens.budgettracker



import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
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

// ---------- Activity ----------
class BudgetTrackerActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NovaMoneyTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    BudgetTrackerApp(rememberNavController())
                }
            }
        }
    }
}

// ---------- Data Models ----------
data class Category(val name: String, var budget: Float, var spent: Float, val color: Color)
data class Transaction(val id: Int, val category: String, val amount: Float, val date: String, val note: String)

// ---------- Main App ----------
@Composable
fun BudgetTrackerApp(navController: NavController) {
    var selectedTab by remember { mutableStateOf("Home") }

    Scaffold(
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
                    label = { Text("Home") },
                    selected = selectedTab == "Home",
                    onClick = {
                        navController.navigate(ROUTE_HOME)
                        selectedTab = "Home" }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Person, contentDescription = "Profile") },
                    label = { Text("Profile") },
                    selected = selectedTab == "Profile",
                    onClick = {
                        navController.navigate(ROUTE_PROFILE)
                        selectedTab = "Profile" }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Notifications, contentDescription = "Alerts") },
                    label = { Text("Alerts") },
                    selected = selectedTab == "Alerts",
                    onClick = {
                        navController.navigate(ROUTE_NOTIFICATION)
                        selectedTab = "Alerts" }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.AccountCircle, contentDescription = "Account") },
                    label = { Text("Account") },
                    selected = selectedTab == "Account",
                    onClick = {
                        navController.navigate(ROUTE_ACCOUNT)
                        selectedTab = "Account" }
                )
            }
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            when (selectedTab) {
                "Home" -> SummaryScreen(navController)
                "AI" -> TransactionFeed(navController)
                "Profile" -> BudgetSettingsScreen(navController)
                "Alerts" -> AlertsScreen(navController)
                "Account" -> AccountScreen(navController)
            }
        }
    }
}

// ---------- Top Bar Wrapper ----------
@Composable
fun ScreenWithTopBar(title: String, onBack: () -> Unit, content: @Composable () -> Unit) {
    Column {
        Surface(
            tonalElevation = 4.dp,
            modifier = Modifier.fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .padding(horizontal = 16.dp),
                contentAlignment = Alignment.Center
            ) {
                IconButton(onClick = onBack) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                }
                Text(title, style = MaterialTheme.typography.titleLarge)
            }
        }

        // ✅ Include your content here:
        Box(modifier = Modifier.fillMaxSize()) {
            content()
        }
    }
}

// ---------- Summary Screen ----------
@Composable
fun SummaryScreen(navController: NavController) {
    ScreenWithTopBar(title = "Budget Summary", onBack = { navController.popBackStack() }) {
        val categories = listOf(
            Category("Food", 500f, 320f, Color(0xFF4CAF50)),
            Category("Transport", 200f, 120f, Color(0xFFFF9800)),
            Category("Entertainment", 300f, 280f, Color(0xFF2196F3))
        )

        val totalBudget = categories.map { it.budget }.sum()
        val totalSpent = categories.map { it.spent }.sum()
        val remaining = totalBudget - totalSpent
        val progress = (totalSpent / totalBudget).coerceIn(0f, 1f)

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text("Total Budget: \$${totalBudget}")
            Text("Spent: \$${totalSpent}")
            Text("Remaining: \$${remaining}")

            Spacer(modifier = Modifier.height(8.dp))

            LinearProgressIndicator(
                progress = progress,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(20.dp)
                    .clip(RoundedCornerShape(10.dp)),
                color = MaterialTheme.colorScheme.primary,
                trackColor = MaterialTheme.colorScheme.primaryContainer
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text("Category Breakdown", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(12.dp))

            categories.forEach { category ->
                Text("${category.name}: \$${category.spent} / \$${category.budget}")
                LinearProgressIndicator(
                    progress = (category.spent / category.budget).coerceIn(0f, 1f),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(12.dp)
                        .clip(RoundedCornerShape(6.dp))
                        .padding(vertical = 4.dp),
                    color = category.color,
                    trackColor = category.color.copy(alpha = 0.3f)
                )
            }
        }
    }
}

// ---------- Transaction Feed ----------
@Composable
fun TransactionFeed(navController: NavController) {
    ScreenWithTopBar(title = "Transactions", onBack = { navController.popBackStack() }) {
        val transactions = listOf(
            Transaction(1, "Food", 25f, "2025-05-01", "Lunch"),
            Transaction(2, "Transport", 15f, "2025-05-02", "Taxi"),
            Transaction(3, "Entertainment", 50f, "2025-05-03", "Movies")
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            items(transactions) { txn ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text("${txn.category} - \$${txn.amount}", fontWeight = FontWeight.Bold)
                            Text(txn.note, fontSize = 12.sp)
                            Text(txn.date, fontSize = 12.sp, color = Color.Gray)
                        }
                    }
                }
            }
        }
    }
}

// ---------- Budget Settings ----------
@Composable
fun BudgetSettingsScreen(navController: NavController) {
    ScreenWithTopBar(title = "Budget Settings", onBack = { navController.popBackStack() }) {
        val categories = remember {
            mutableStateListOf(
                Category("Food", 500f, 320f, Color(0xFF4CAF50)),
                Category("Transport", 200f, 120f, Color(0xFFFF9800)),
                Category("Entertainment", 300f, 280f, Color(0xFF2196F3))
            )
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            items(categories) { category ->
                var budgetText by remember { mutableStateOf(category.budget.toString()) }

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Text(category.name, fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.height(4.dp))
                        OutlinedTextField(
                            value = budgetText,
                            onValueChange = { input ->
                                budgetText = input
                                input.toFloatOrNull()?.let { category.budget = it }
                            },
                            label = { Text("Budget Amount") }
                        )
                        Button(
                            onClick = {
                                // Save updates here if needed
                            },
                            modifier = Modifier.padding(top = 8.dp)
                        ) {
                            Text("Save Budget")
                        }
                    }
                }
            }
        }
    }
}

// ---------- Placeholder Screens ----------
@Composable
fun AlertsScreen(navController: NavController) {
    ScreenWithTopBar(title = "Alerts", onBack = { navController.popBackStack() }) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("Alerts Screen Coming Soon")
        }
    }
}

@Composable
fun AccountScreen(navController: NavController) {
    ScreenWithTopBar(title = "Account", onBack = { navController.popBackStack() }) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("Account Screen Coming Soon")
        }
    }
}

// ---------- Previews ----------
@Preview(showBackground = true)
@Composable
fun SummaryPreview() {
    NovaMoneyTheme { SummaryScreen(rememberNavController()) }
}

@Preview(showBackground = true)
@Composable
fun TransactionFeedPreview() {
    NovaMoneyTheme { TransactionFeed(rememberNavController()) }
}

@Preview(showBackground = true)
@Composable
fun BudgetSettingsPreview() {
    NovaMoneyTheme { BudgetSettingsScreen(rememberNavController()) }
}



